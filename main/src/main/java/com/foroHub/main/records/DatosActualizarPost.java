package com.foroHub.main.records;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPost(
        @NotNull Long id,
        String mensaje,
        String titulo
) {
}
