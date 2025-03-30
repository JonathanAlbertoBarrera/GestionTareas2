package com.example.GestionTareas2.Usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuariosById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> guardarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.crearUsuario(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> modificarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.modificarUsuario(id,usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.eliminarUsuario(id));
    }
}
