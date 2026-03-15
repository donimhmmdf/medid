package com.ibm.medid.dto;

import java.util.Date;

import com.ibm.medid.enums.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterUserResponse {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private Role role;

}
