package com.ams.likepost.DAO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "mm-dd-yyyy")
    Date dateOfBirth;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    Country country;
}
