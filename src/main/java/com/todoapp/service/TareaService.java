package com.todoapp.service;


import com.todoapp.entity.Tarea;
import com.todoapp.respository.TareaRepository;
import com.todoapp.respository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;

    public Tarea newTarea (Tarea tarea){
        return tareaRepository.save(tarea);
    }

    public Tarea findById (Long id){
        return tareaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarea inexistente"));
    }

    public List<Tarea> getAllTarea (){
        return tareaRepository.findAll();
    }

    public Tarea updateTarea (Tarea tarea){
        return tareaRepository.save(tarea);
    }

    public void deleteTarea (Long id){

        findById(id);
        tareaRepository.deleteById(id);

    }

    public List<Tarea> getAllTareaByUsuarioId (Long id){
        return tareaRepository.findByUsuarioId(id);
    }




}
