package com.ibm.medid.model;

import java.util.Date;

import com.ibm.medid.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails extends BaseProperties{
    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
