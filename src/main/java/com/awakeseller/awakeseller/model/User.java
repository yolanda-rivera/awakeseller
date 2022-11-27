package com.awakeseller.awakeseller.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
