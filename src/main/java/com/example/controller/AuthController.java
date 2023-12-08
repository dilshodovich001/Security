package com.example.controller;

import com.example.dto.auth.AuthenticationRequestDTO;
import com.example.dto.auth.AuthenticationResponseDTO;
import com.example.dto.auth.RegisterRequestDTO;
import com.example.service.AuthService;
import com.example.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRequestDTO> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/verification/email/{jtwToken}")
    public ResponseEntity<String> emailVerification(@PathVariable("jtwToken") String jwt) {
        String response =emailService.verification(jwt);
        return ResponseEntity.ok(response);
    }
}
