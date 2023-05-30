package com.todoapp.controller;

import com.todoapp.config.security.*;
import com.todoapp.entity.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final AuthenticationService authService;

    //    private final UsuarioService usuarioService;

    @PostMapping("auth/registro")
    public ResponseEntity<AuthenticationResponse> registroUsuario (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try {
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", usuario.getUsername());
        response.put("nombre", usuario.getNombre());
        response.put("apellido", usuario.getApellido());

        return ResponseEntity.ok().body(response);
    }

}
