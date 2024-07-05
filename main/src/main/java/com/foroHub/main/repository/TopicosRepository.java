package com.foroHub.main.repository;

import com.foroHub.main.modelo.Post;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicosRepository extends JpaRepository<Post, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Post> findAll(Pageable pageable);

    Post findPostById(Long id);

    Post deletePostById(Long id);
}
