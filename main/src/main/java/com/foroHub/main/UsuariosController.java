package com.foroHub.main;

import com.foroHub.main.exceptions.Dupicaciones;
import com.foroHub.main.modelo.Post;
import com.foroHub.main.records.DatosActualizarPost;
import com.foroHub.main.records.DatosNuevoPost;
import com.foroHub.main.records.DatosPostListado;
import com.foroHub.main.repository.TopicosRepository;
import com.foroHub.main.services.TopicsServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;

@RestController
@RequestMapping("/posts")
public class UsuariosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    TopicsServices topicsServices;

    @PostMapping
    ResponseEntity<String> nuevoPost(@RequestBody @Valid DatosNuevoPost datosNuevoPost){
    topicosRepository.save(new Post(datosNuevoPost));
        return ResponseEntity.ok("El registro fue exitoso");

    }

    @GetMapping
    public Page<DatosPostListado> listadoPost(@PageableDefault(size = 10)Pageable pageable){
        return topicosRepository.findAll(pageable).map(DatosPostListado::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> ResumenPost(@PathVariable("id") Long id){
        Post post = topicosRepository.findPostById(id);
        if ( post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }


    @Transactional
    @PutMapping
    public void  actualizacionPost( @RequestBody @Valid DatosActualizarPost datosActualizarPost){
        Post post = topicosRepository.getReferenceById(datosActualizarPost.id());
            post.actualizarDatos(datosActualizarPost);
    }

    @ExceptionHandler(Dupicaciones.class)
    public ResponseEntity<String> dupicaciones(Dupicaciones ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> eliminarPost(@PathVariable("id") Long id){
        Post post = topicosRepository.findPostById(id);
    topicosRepository.delete(post);
        return ResponseEntity.ok(post);
    }
}
