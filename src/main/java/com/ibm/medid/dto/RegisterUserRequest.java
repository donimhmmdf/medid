package com.ibm.medid.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.medid.model.validation.group.DoctorValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank
    @Size(min = 3,max = 20)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank
    @Size(max = 1)
    private String gender;

    @NotNull
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotBlank
    private String phone;

    private String address;

    @NotBlank(groups = DoctorValidation.class)
    private String licenseNumber;

    @NotBlank(groups = DoctorValidation.class)
    private Long specializationId;

    @NotBlank(groups = DoctorValidation.class)
    private Integer yearsOfExperience;

    @NotBlank(groups = DoctorValidation.class)
    private String education;

    @NotBlank(groups = DoctorValidation.class)
    private Long hospital_id;

    @NotBlank(groups = DoctorValidation.class)
    private String status;


}
