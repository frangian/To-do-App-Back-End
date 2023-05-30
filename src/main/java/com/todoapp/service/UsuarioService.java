package com.todoapp.service;

import com.todoapp.entity.Usuario;
import com.todoapp.respository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

//    public Usuario getUsuario (Usuario usuario){
//        return usuarioRepository.findById(Usuario usuario.getId());
//    }

}
