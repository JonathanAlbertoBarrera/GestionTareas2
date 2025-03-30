package com.example.GestionTareas2.Tarea;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<TareaResponseDTO>> obtenerTodasLasTareas(){
        return ResponseEntity.status(HttpStatus.OK).body(tareaService.todasLasTareas());
    }

    @GetMapping("/{idTarea}")
    public ResponseEntity<TareaResponseDTO> buscarTareaPorId(@PathVariable Long idTarea){
        return ResponseEntity.status(HttpStatus.OK).body(tareaService.tareaPorId(idTarea));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<TareaResponseDTO>> buscarTareaPorUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(tareaService.tareaPorUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crearTarea(@Valid @RequestBody TareaRequestDTO tareaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crearTarea(tareaRequestDTO));
    }

    @PutMapping("/{idTarea}")
    public ResponseEntity<TareaResponseDTO> modificarTarea(@PathVariable Long idTarea, @Valid @RequestBody TareaRequestDTO tareaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(tareaService.modificarTarea(idTarea,tareaRequestDTO));
    }

    @DeleteMapping("/{idTarea}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long idTarea){
        return ResponseEntity.status(HttpStatus.OK).body(tareaService.eliminarTarea(idTarea));
    }

}
