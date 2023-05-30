package com.todoapp.controller;

import com.todoapp.entity.Tarea;
import com.todoapp.entity.Usuario;
import com.todoapp.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/tasks")
public class TareaController {

    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<?> newTarea (@RequestBody Tarea tarea){
        return ResponseEntity.ok(tareaService.newTarea(tarea));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTarea (){
        return ResponseEntity.ok(tareaService.getAllTarea());
    }

    @GetMapping
    public ResponseEntity<?> getAllTareaByUsuarioId (@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(tareaService.getAllTareaByUsuarioId(usuario.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarea (@PathVariable Long id, @RequestBody Tarea tarea){
        try {
            Tarea tareaExistente = tareaService.findById(id);
            tareaExistente.setDescription(tarea.getDescription());
            tareaExistente.setCompleted(tarea.isCompleted());
            Tarea tareaActualizada = tareaService.updateTarea(tareaExistente);
            return ResponseEntity.ok(tareaActualizada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea (@PathVariable Long id){
        try {
            tareaService.deleteTarea(id);
            return ResponseEntity.ok("La tarea fue eliminada correctamente");
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
