package com.example.GestionTareas2.Usuario;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper=new ModelMapper();

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios=usuarioRepository.findAll();

        return usuarios.stream()
                .map(user->modelMapper.map(user, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Transactional
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario= Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .rol(usuarioDTO.getRol())
                .build();
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    @Transactional
    public UsuarioDTO modificarUsuario(Long id, UsuarioDTO usuarioDTO){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setRol(usuarioDTO.getRol());
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Transactional
    public String eliminarUsuario(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        usuarioRepository.deleteById(id);
        return "Usuario eliminado correctamente";
    }

}
