package com.smoothstack.utopia.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    @Getter
    @Setter
    private UserRole role;

    @NotNull
    @Size(max = 255)
    @Column(name = "given_name")
    @Getter
    @Setter
    private String givenName;

    @NotNull
    @Size(max = 255)
    @Column(name = "family_name")
    @Getter
    @Setter
    private String familyName;

    @NotNull
    @Size(max = 45)
    @Getter
    @Setter
    private String username;

    @NotNull
    @Size(max = 255)
    @Getter
    @Setter
    private String email;

    @NotNull
    @Size(max = 255)
    @Getter
    @Setter
    private String password;

    @NotNull
    @Size(max = 45)
    @Getter
    @Setter
    private String phone;
}
