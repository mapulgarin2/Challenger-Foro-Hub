package com.challengerforohu.forohub_1.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private String nombre;
    private String curso;

    public Topico(DatosRegistroTopico datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = datos.status();
        this.nombre = datos.nombre();
        this.curso = datos.curso();
    }

    public void actualizarInformacionTopico(@Valid DatosActualizarTopico datos) {
        if(datos.Titulo() != null){
            this.titulo = datos.Titulo();
        }
        if (datos.Mensaje() != null) {
            this.mensaje = datos.Mensaje();
        }
        if (datos.status() != null ) {
            this.status = datos.status();
        }
        if (datos.nombre()!= null ) {
            this.nombre = datos.nombre();
        }
        if (datos.curso()!= null ) {
            this.curso = datos.curso();
        }
    }
}
