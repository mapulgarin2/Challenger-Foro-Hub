package com.challengerforohu.forohub_1.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String Titulo,
        String Mensaje,
        StatusTopico status,
        String nombre,
        String curso) {
}
