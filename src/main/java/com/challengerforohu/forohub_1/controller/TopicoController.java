package com.challengerforohu.forohub_1.controller;

import com.challengerforohu.forohub_1.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;


    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos , UriComponentsBuilder uriComponentsBuilder) {
        // Verificamos si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity
                    .badRequest()
                    .body("Ya existe un tópico registrado con ese título y mensaje.");
        }

        // Si no existe, lo guardamos
        var topico = new Topico(datos);
        topicoRepository.save(topico);

        // Construimos la URI con el ID del nuevo tópico
        var uri = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        // Devolvemos una respuesta 201 Created con los datos
        return ResponseEntity
                .created(uri)
                .body(topico); // puedes usar un DTO si lo prefieres

    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopicos>> listarTopicos(@PageableDefault(size = 10,sort = "fechaCreacion")Pageable paginacion) {
        var page = topicoRepository.findAll(paginacion).map(DatosListarTopicos :: new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        Optional<Topico> topicoOptional = topicoRepository.findById(datos.id());
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topicoExistente = topicoOptional.get();
        if (topicoRepository.existsByTituloAndMensaje(datos.Titulo(), datos.Mensaje())) {
            return ResponseEntity
                    .badRequest()
                    .body("Ya existe un tópico registrado con ese título y mensaje.");
        }
        topicoExistente.actualizarInformacionTopico(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topicoExistente));
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Topico topico = topicoOptional.get();
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

}
