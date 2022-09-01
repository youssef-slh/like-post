package com.ams.likepost.Service;

import com.ams.likepost.DAO.EmailVerificationToken;
import com.ams.likepost.DAO.User;
import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.DAO.UserSession;
import com.ams.likepost.Repository.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserSecurityService {
    @Autowired
    public UserSecurityRepo userSecurityRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public EmailVerificationTokenService emailVerificationTokenService;

    @Autowired
    public EmailSenderService emailSenderService;

    public List<UserSecurity> getAll() {
        List<UserSecurity> all = userSecurityRepo.findAll();
        return all;
    }

    public Optional<UserSecurity> getById(long id) {
        return userSecurityRepo.findById(id);
    }

    public Optional<UserSecurity> getUserByUsername(String username) {
        return userSecurityRepo.findByUserName(username);
    }

    public Optional<UserSecurity> getUserByEmail(String email) {
        return userSecurityRepo.findByEmail(email);
    }

    public String register(UserSecurity userSecurity) {

        //  userEntity.setExpired(false);
        userSecurity.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
        userSecurityRepo.save(userSecurity);

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        //  String token = UUID.randomUUID().toString();
        String token = String.format("%06d", number);

        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(5))
                .userSecurity(userSecurity)
                .build();


        emailVerificationTokenService.save(emailVerificationToken);

        String emailBody = "Google Verification Code\t\n" +
                "Dear LikePost User,\n" +
                "\n" +
                "We received a request to register your LikePost Account through your email address. Your LikePost verification code is:\n" +
                "\n" +
                token +
                "\n" +
                "If you did not request this code, it is possible that someone else is trying to access the LikePost Account . Do not forward or give this code to anyone.\n" +
                "\n" +
                "Sincerely yours,\n" +
                "\n" +
                "The LikePost Accounts team";
        emailSenderService.sendSimpleEmail(userSecurity.getEmail(), emailBody, "LikePost");

        return userSecurity.getEmail() + " is saved successfully";

    }

    public String updateUser(UserSecurity userSecurity) {
        userSecurityRepo.save(userSecurity);
        return userSecurity.getEmail() + " is updated successfully";
    }

    public String deleteUser(long id) {
        userSecurityRepo.deleteById(id);
        return "user security with id " + id + " is updated successfully";
    }

    public void enableUser(Long id) {
        userSecurityRepo.enableUser(id);
    }
}
