package com.example.GestionTareas2.Tarea;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TareaRequestDTO {
    @NotBlank(message = "Debes ingresar un nombre blo")
    private String titulo;
    @NotBlank(message = "Debes ingresar una descripcion blo")
    @Size(max = 80, message = "No blo es mucho texto, quieres romper mi bd")
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean completada;
    @NotNull(message = "Debes ingresar de quien es la tarea")
    private Long idUsuario;
}
