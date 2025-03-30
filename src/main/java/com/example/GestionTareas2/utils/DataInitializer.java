package com.example.GestionTareas2.utils;

import com.example.GestionTareas2.Usuario.Usuario;
import com.example.GestionTareas2.Usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    private void init(){
        if(usuarioRepository.count()==0){
            Usuario usuario1= Usuario.builder()
                    .nombre("Jonathan Barrera")
                    .rol("Administrador")
                    .build();
            usuarioRepository.save(usuario1);
            Usuario usuario2=Usuario.builder()
                    .nombre("Valeria B")
                    .rol("Usuario")
                    .build();
            usuarioRepository.save(usuario2);
        }
    }
}
