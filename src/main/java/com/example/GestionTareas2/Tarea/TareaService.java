package com.example.GestionTareas2.Tarea;

import com.example.GestionTareas2.Usuario.Usuario;
import com.example.GestionTareas2.Usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper=new ModelMapper();
    {
        modelMapper.typeMap(Tarea.class, TareaResponseDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUsuario().getNombre(),
                    TareaResponseDTO::setNombreUsuario);
        });
    }

    @Transactional(readOnly = true)
    public List<TareaResponseDTO> todasLasTareas(){
        List<Tarea> tareas=tareaRepository.findAll();
        return tareas.stream()
                .map(tarea-> modelMapper.map(tarea,TareaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TareaResponseDTO tareaPorId(Long idTarea){
        Tarea tarea=tareaRepository.findById(idTarea)
                .orElseThrow(()-> new EntityNotFoundException("Tarea no encontrada"));
        return modelMapper.map(tarea,TareaResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<TareaResponseDTO> tareaPorUsuario(Long idUsuario){
        Usuario usuario=usuarioRepository.findById(idUsuario)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));

        List<Tarea> tareasporUsuario=tareaRepository.findByUsuario(usuario);
        return tareasporUsuario.stream()
                .map(tarea -> modelMapper.map(tarea, TareaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public TareaResponseDTO crearTarea(TareaRequestDTO tareaRequestDTO){
        //ver si existe el usuario para asignarle la tarea
        Usuario usuario=usuarioRepository.findById(tareaRequestDTO.getIdUsuario())
                .orElseThrow(()-> new EntityNotFoundException());

        //se crea la nueva tarea
        Tarea nuevaTarea= Tarea.builder()
                .titulo(tareaRequestDTO.getTitulo())
                .descripcion(tareaRequestDTO.getDescripcion())
                .fechaCreacion(tareaRequestDTO.getFechaCreacion())
                .completada(false)
                .usuario(usuario)
                .build();
        //se guarda pero se retorna el dto de respuesta
        return modelMapper.map(tareaRepository.save(nuevaTarea), TareaResponseDTO.class);
    }

    @Transactional
    public TareaResponseDTO modificarTarea(Long idTarea,TareaRequestDTO tareaRequestDTO){
        // aqui veo si existe la tarea segun el id que mandenn
        Tarea tarea=tareaRepository.findById(idTarea)
                .orElseThrow(()->new EntityNotFoundException("Tarea no encontrada"));
        //checo si el usuario existe segun el id que manden
        Usuario usuario=usuarioRepository.findById(tareaRequestDTO.getIdUsuario())
                        .orElseThrow(()->new EntityNotFoundException("Usuario no encontrado"));
        //seteo lo que mandan en el dto en la tarea indicada
        tarea.setTitulo(tareaRequestDTO.getTitulo());
        tarea.setDescripcion(tareaRequestDTO.getDescripcion());
        tarea.setFechaCreacion(tareaRequestDTO.getFechaCreacion());
        tarea.setCompletada(tareaRequestDTO.isCompletada());
        tarea.setUsuario(usuario);
        //ahora si se guarda pero devolviendo de respuesta el dto tarearesponsedto
        return modelMapper.map(tareaRepository.save(tarea), TareaResponseDTO.class);
    }

    public String eliminarTarea(Long idTarea){
        if(tareaRepository.existsById(idTarea)){
            tareaRepository.deleteById(idTarea);
            return "Tarea eliminada exitosamente";
        }else{
            return "No se encontro la tarea blo, usa otro ID";
        }
    }

}
