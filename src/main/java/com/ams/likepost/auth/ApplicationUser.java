package com.ams.likepost.auth;

import com.ams.likepost.DAO.User;
import com.ams.likepost.DAO.UserSecurity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class ApplicationUser implements UserDetails {

    private UserSecurity appUser;

    @Autowired
    public ApplicationUser(UserSecurity ap) {
        this.appUser = ap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<RoleEntity> roles = appUser.getRole();
//        List<SimpleGrantedAuthority> auth = new ArrayList<>();
//        for (RoleEntity role : roles) {
//            auth.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        }
//
//        return auth;
        return null;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.getIsActive();
    }

}
