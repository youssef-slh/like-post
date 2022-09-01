package com.ams.likepost.DAO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String code;
}
