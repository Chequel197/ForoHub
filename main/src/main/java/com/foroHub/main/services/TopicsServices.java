package com.foroHub.main.services;


import com.foroHub.main.modelo.Post;
import com.foroHub.main.records.DatosNuevoPost;
import com.foroHub.main.repository.TopicosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class TopicsServices {

    @Autowired
    TopicosRepository topicosRepository;

    @Transactional
    public void verificaicon(DatosNuevoPost datosNuevoPost){
        if (topicosRepository.existsByTituloAndMensaje(datosNuevoPost.titulo(), datosNuevoPost.mensaje())){
            throw new DuplicateKeyException("Otro post tiene el mismo mensaje y titutlo");
        }
        Post post = new Post();
        post.setTitulo(datosNuevoPost.titulo());
        post.setMensaje(datosNuevoPost.mensaje());
        topicosRepository.save(new Post(datosNuevoPost));
    }


}
