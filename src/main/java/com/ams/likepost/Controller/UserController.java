package com.ams.likepost.Controller;

import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.Service.EmailValidator;
import com.ams.likepost.Service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private EmailValidator emailValidator;

    @PostMapping
    public String saveUser(@RequestBody UserSecurity userSecurity){
        Optional<UserSecurity> userSecurity1= userSecurityService.getUserByEmail(userSecurity.getEmail());

        if(!emailValidator.test(userSecurity.getEmail())){
            throw  new IllegalArgumentException("email is not valid");
        }

        if(userSecurity1.isPresent()){
            throw  new IllegalArgumentException("account with specified email already registered");
        }

        userSecurity.setIsActive(false);

        userSecurityService.register(userSecurity);
        return "EmailVerification";
    }

}
