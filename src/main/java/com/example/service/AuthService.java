package com.example.service;

import com.example.dto.auth.AuthenticationRequestDTO;
import com.example.dto.auth.AuthenticationResponseDTO;
import com.example.dto.auth.RegisterRequestDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exp.EmailAlreadyExistsException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService  {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;


    public RegisterRequestDTO register(RegisterRequestDTO request) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(request.getEmail());
        if (optional.isPresent()) {

            if (optional.get().getStatus().equals(ProfileStatus.BLOCK)) {
                profileRepository.delete(optional.get());
            } else {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        }

        var profile = ProfileEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ProfileRole.USER)
                .status(ProfileStatus.BLOCK)
                .build();
        profileRepository.save(profile);
        Thread thread = new Thread(){
            @Override
            public void run() {
                emailService.sendVerificationEmail(profile);
            }
        };
        thread.start();


        return request;
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        var profile = profileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ItemNotFoundException("Not found user"));

        var jwtToken = jwtService.generateToken(profile);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }



}