package com.example.GestionTareas2.Tarea;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TareaResponseDTO {
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean completada;
    private String nombreUsuario;
}
