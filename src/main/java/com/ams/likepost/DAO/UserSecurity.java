package com.ams.likepost.DAO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user_security")
public class UserSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String phoneNumber;
    String email;
    String password;
    Boolean isLocked;
    Boolean isActive;
    Boolean isExpired;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
}
