package com.ams.likepost.Service;

import com.ams.likepost.DAO.User;
import com.ams.likepost.DAO.UserSession;
import com.ams.likepost.Repository.UserRepo;
import com.ams.likepost.Repository.UserSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSessionService {
    @Autowired
    public UserSessionRepo userSessionRepo;

    public List<UserSession> getAll() {
        List<UserSession> all = userSessionRepo.findAll();
        return all;
    }

    public Optional<UserSession> getById(long id) {
        return userSessionRepo.findById(id);
    }

    public String save(UserSession userSession) {

          //  userEntity.setExpired(false);
        userSessionRepo.save(userSession);

            return " event is saved successfully";

    }

    public String update(UserSession userSession) {
        userSessionRepo.save(userSession);
        return  " is updated successfully";
    }

    public String delete(long id) {
        userSessionRepo.deleteById(id);
        return "user session with id " + id + " is updated successfully";
    }
}
