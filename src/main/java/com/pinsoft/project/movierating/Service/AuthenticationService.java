package com.pinsoft.project.movierating.Service;

import com.pinsoft.project.movierating.DTO.AuthenticationRequest;
import com.pinsoft.project.movierating.DTO.AuthenticationResponse;
import com.pinsoft.project.movierating.DTO.RegisterRequest;
import com.pinsoft.project.movierating.Entity.Role;
import com.pinsoft.project.movierating.Entity.User;
import com.pinsoft.project.movierating.Repository.RoleRepository;
import com.pinsoft.project.movierating.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAccount_active(true);
        user.setBase64image(request.getBase64image());
        Role role = roleRepository.findByName("user").get(0);
        user.setRole(role);
        User savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(savedUser.getId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .build();
    }
}