package com.ams.likepost.Repository;

import com.ams.likepost.DAO.EmailVerificationToken;
import com.ams.likepost.DAO.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepo extends JpaRepository<EmailVerificationToken,Long> {
    @Query(value = "select * from email_verification_token t where id=(select max(t1.id) from email_verification_token t1\n" +
            "                                                                          join user_security sec on sec.id=t1.userSecurityId\n" +
            "                                                                          where sec.email= :email)", nativeQuery = true)
    Optional<EmailVerificationToken> getLastTokenForUser(@Param("email") String email);
}
