package com.ams.likepost.Controller;

import com.ams.likepost.DAO.EmailVerificationToken;
import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.DTO.EmailVerificationRequest;
import com.ams.likepost.Service.EmailValidator;
import com.ams.likepost.Service.EmailVerificationTokenService;
import com.ams.likepost.Service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/EmailVerificationToken")
public class EmailVerificationController {

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;

    @Autowired
    private UserSecurityService userSecurityService;

    @PostMapping
    public ResponseEntity<Boolean> verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {

        if (!emailValidator.test(emailVerificationRequest.getEmail())) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }

        Optional<EmailVerificationToken> emailVerificationToken =emailVerificationTokenService.getLAstByEmail(emailVerificationRequest.getEmail());

        if(!emailVerificationToken.isPresent()){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }

        if(!emailVerificationRequest.getToken().equals(emailVerificationToken.get().getToken())){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }

        if(emailVerificationToken.get().getExpiredAt().isBefore(LocalDateTime.now())){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }

        emailVerificationToken.get().setConfirmedAt(LocalDateTime.now());

        emailVerificationTokenService.update(emailVerificationToken.get());

        userSecurityService.enableUser(emailVerificationToken.get().getUserSecurity().getId());

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
