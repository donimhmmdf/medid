package com.ibm.medid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.medid.dto.LoginUserRequest;
import com.ibm.medid.dto.RegisterUserRequest;
import com.ibm.medid.dto.RegisterUserResponse;
import com.ibm.medid.dto.WebResponse;
import com.ibm.medid.enums.Role;
import com.ibm.medid.security.AuthenticationService;
import com.ibm.medid.utility.ResponseUtil;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final ResponseUtil responseUtil;

    public AuthController(AuthenticationService authenticationService, ResponseUtil responseUtil) {
        this.authenticationService = authenticationService;
        this.responseUtil = responseUtil;
    }

    @PostMapping("/patient/register")
    public ResponseEntity<WebResponse<RegisterUserResponse>> registerPatient(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse register = authenticationService.register(request, Role.PATIENT);;
        return responseUtil.successResponse("Register success", register);
    }

    @PostMapping("login")
    public ResponseEntity<WebResponse<Map<String,Object>>> login(@RequestBody LoginUserRequest request) {
     Map<String,Object> loginResponse = authenticationService.login(request);
     return responseUtil.successResponse("Login success", loginResponse);
    }
    
    
}
