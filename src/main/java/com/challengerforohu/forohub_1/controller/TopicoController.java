package com.challengerforohu.forohub_1.controller;

import com.challengerforohu.forohub_1.topico.DatosListarTopicos;
import com.challengerforohu.forohub_1.topico.DatosRegistroTopico;
import com.challengerforohu.forohub_1.topico.Topico;
import com.challengerforohu.forohub_1.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

}
