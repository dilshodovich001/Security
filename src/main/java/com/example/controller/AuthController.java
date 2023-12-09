package com.example.controller;


import com.example.dto.auth.AuthRegistrationDTO;
import com.example.dto.auth.AuthRequestDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody AuthRegistrationDTO dto){
        log.info("Registration --> " + dto);
        String response = authService.registration(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    private ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto){
        log.info("Authorization --> "+dto);
        AuthResponseDTO response  = authService.login(dto);
        return ResponseEntity.ok(response);
    }




}
