package com.challengerforohu.forohub_1.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String Titulo,
        String Mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String nombre,
        String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),topico.getNombre(),topico.getCurso());
    }
}
