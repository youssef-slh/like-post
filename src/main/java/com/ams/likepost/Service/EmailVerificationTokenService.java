package com.ams.likepost.Service;

import com.ams.likepost.DAO.EmailVerificationToken;
import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.Repository.EmailVerificationTokenRepo;
import com.ams.likepost.Repository.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailVerificationTokenService {
    @Autowired
    public EmailVerificationTokenRepo emailVerificationTokenRepo;


    public List<EmailVerificationToken> getAll() {
        List<EmailVerificationToken> all = emailVerificationTokenRepo.findAll();
        all.stream().map(emailVerificationToken -> new EmailVerificationToken()).collect(Collectors.toList());
        return all;
    }

    public Optional<EmailVerificationToken> getById(long id) {
        return emailVerificationTokenRepo.findById(id);
    }

    public Optional<EmailVerificationToken> getLAstByEmail(String email) {
        return emailVerificationTokenRepo.getLastTokenForUser(email);
    }

    public String save(EmailVerificationToken emailVerificationToken) {

          //  userEntity.setExpired(false);
        emailVerificationTokenRepo.save(emailVerificationToken);

            return emailVerificationToken.getToken()+ " is saved successfully";

    }

    public String update(EmailVerificationToken emailVerificationToken) {
        emailVerificationTokenRepo.save(emailVerificationToken);
        return emailVerificationToken.getToken()  + " is updated successfully";
    }

    public String delete(long id) {
        emailVerificationTokenRepo.deleteById(id);
        return "email verification token with id " + id + " is updated successfully";
    }
}
