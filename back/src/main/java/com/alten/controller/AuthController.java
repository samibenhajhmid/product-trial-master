package com.alten.controller;

import com.alten.DTO.AccountRequest;
import com.alten.DTO.LoginRequest;
import com.alten.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @Operation(summary = "Créer un compte utilisateur")
    @PostMapping("/account")
    public ResponseEntity<String> register(@RequestBody @Valid AccountRequest request) {
        try {
            authService.registerUser(request);
            return ResponseEntity.ok("Compte créé avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        try {
            String token = authService.authenticateUser(request);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
