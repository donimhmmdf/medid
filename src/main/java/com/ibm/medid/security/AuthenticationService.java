package com.ibm.medid.security;

import com.ibm.medid.service.ValidationService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.medid.dto.LoginUserRequest;
import com.ibm.medid.dto.LoginUserResponse;
import com.ibm.medid.dto.RegisterUserRequest;
import com.ibm.medid.dto.RegisterUserResponse;
import com.ibm.medid.enums.Role;
import com.ibm.medid.model.User;
import com.ibm.medid.model.UserDetails;
import com.ibm.medid.repository.UserDetailsRepository;
import com.ibm.medid.repository.UserRepository;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final ValidationService validationService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, UserDetailsRepository userDetailsRepository,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
            ValidationService validationService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.validationService = validationService;
        this.jwtService = jwtService;
    }

    public RegisterUserResponse register(RegisterUserRequest request, Role role) {
        validationService.validate(request);

        if (role == Role.DOCTOR) {
            validationService.validate(request); // group doctor
        }

        try {

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword())).build();

            User newUser = userRepository.save(user);

            UserDetails userDetails = UserDetails.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .gender(request.getGender())
                    .dateOfBirth(request.getDateOfBirth())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .role(role)
                    .user(newUser)
                    .build();

            userDetailsRepository.save(userDetails);

            return RegisterUserResponse.builder()
                    .username(userDetails.getUser().getUsername())
                    .fullName(userDetails.getFullName())
                    .email(userDetails.getEmail())
                    .phone(userDetails.getPhone())
                    .dateOfBirth(userDetails.getDateOfBirth())
                    .address(userDetails.getAddress())
                    .role(userDetails.getRole())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("User already exists");
        }

    }

    public Map<String, Object> login(LoginUserRequest request) {
        validationService.validate(request);
        try {
            Map<String, Object> map = new HashMap<>();
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();
            UserDetails userDetails = userDetailsRepository.findById(user.getId()).orElseThrow();
            LoginUserResponse response = LoginUserResponse.builder()
                    .username(user.getUsername())
                    .fullName(userDetails.getFullName())
                    .role(userDetails.getRole())
                    .build();

            map.put("user", response);
            map.put("token", jwtService.generateToken(user));

            return map;
        } catch (Exception e) {
            throw new BadCredentialsException("Wrong username or password");
        }
    }
}
