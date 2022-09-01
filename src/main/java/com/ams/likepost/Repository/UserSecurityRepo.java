package com.ams.likepost.Repository;

import com.ams.likepost.DAO.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserSecurityRepo extends JpaRepository<UserSecurity,Long> {
    @Query("select u from UserSecurity u where u.userName= :username")
    public Optional<UserSecurity> findByUserName(@Param("username") String username);
    @Query("select u from UserSecurity u where u.email= :email")
    public Optional<UserSecurity> findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserSecurity u " +
            "SET u.isActive = true " +
            "WHERE u.id = ?1")
    int enableUser(Long id);
}
