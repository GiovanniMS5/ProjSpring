package com.sistemaAutoEscola.autoescola.controller;

import com.sistemaAutoEscola.autoescola.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

record LoginRequest(String username, String password) {}
record LoginResponse(String token) {}

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

     

        try {
            // Autentica o usuário com o AuthenticationManager do Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            // Obtém os detalhes do usuário autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token JWT
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException ex) {
            
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno: " + ex.getMessage());
        }
    }
}
