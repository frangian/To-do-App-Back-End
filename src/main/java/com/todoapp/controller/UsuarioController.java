package com.todoapp.controller;

import com.todoapp.config.security.AuthenticationRequest;
import com.todoapp.config.security.AuthenticationResponse;
import com.todoapp.config.security.AuthenticationService;
import com.todoapp.config.security.RegisterRequest;
import com.todoapp.entity.Usuario;
import com.todoapp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final AuthenticationService authService;

    @PostMapping("auth/registro")
    public ResponseEntity<AuthenticationResponse> registroUsuario (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        log.info("controller");
        try {
        log.info("controller");
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<String> buscarUsuario(){
        return ResponseEntity.ok("Usuario");
    }

}
