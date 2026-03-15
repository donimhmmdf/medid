package com.ibm.medid.dto;

import com.ibm.medid.enums.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginUserResponse {
    private String username;
    private String fullName;
    private Role role;
}
