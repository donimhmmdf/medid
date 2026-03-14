package com.ibm.medid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseProperties{

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

}
