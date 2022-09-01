package com.ams.likepost.DAO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_session")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "mm-dd-yyyy hh:mm:ss")
    Date eventDate;
    String eventType;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
}
