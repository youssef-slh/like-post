package com.ams.likepost.auth;

import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.Repository.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserSecurityRepo userSecurityRepo;

    private final static String USER_NOT_FOUND = "User not found";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserSecurity> ap = userSecurityRepo.findByEmail(s);

        return new ApplicationUser(ap.get());

    }



}
