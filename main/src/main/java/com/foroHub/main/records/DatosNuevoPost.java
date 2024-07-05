package com.foroHub.main.records;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

public record DatosNuevoPost(
       @NotNull String titulo,
       @NotNull String mensaje,
       @NotNull String autor
        ) {
}
