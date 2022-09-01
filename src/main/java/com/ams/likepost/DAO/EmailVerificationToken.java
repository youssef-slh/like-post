package com.ams.likepost.DAO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "email_verification_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    LocalDateTime confirmedAt;
    LocalDateTime createdAt;
    LocalDateTime expiredAt;
    @OneToOne
    @JoinColumn(name = "userSecurityId", referencedColumnName = "id")
    UserSecurity userSecurity;
}
