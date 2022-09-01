package com.ams.likepost.Repository;

import com.ams.likepost.DAO.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSession,Long> {
}
