package com.example.GestionTareas2.Tarea;

import com.example.GestionTareas2.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean completada;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
