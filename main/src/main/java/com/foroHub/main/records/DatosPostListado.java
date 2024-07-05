package com.foroHub.main.records;

import com.foroHub.main.modelo.Post;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosPostListado(
        Long id,
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotNull String autor,
        LocalDateTime fechaDeCreacion
) {
    public DatosPostListado(Post post) {
        this(post.getId(), post.getTitulo(), post.getMensaje(), post.getAutor(), post.getFechaDeCreacion());
    }
}
