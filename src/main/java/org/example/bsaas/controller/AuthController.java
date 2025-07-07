package org.example.bsaas.controller;

import org.example.bsaas.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Aqui você faz a verificação do usuário e senha (exemplo básico)
        // No mundo real, use PasswordEncoder e compare hash!

        if ("user".equals(request.getEmail()) && "senha".equals(request.getPassword())) {
            // Retorne token ou info do usuário
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}