package com.challengerforohu.forohub_1.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        StatusTopico status,
        @NotBlank String nombre,
        @NotBlank String curso) {
}
