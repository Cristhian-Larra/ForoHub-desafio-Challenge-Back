package Desafio_ForoHub.ForoHub.controller;

import Desafio_ForoHub.ForoHub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra un topico en la base de datos")
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var response = topicoService.registrarTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DatosDetalleTopico>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un topico por ID")
    public ResponseEntity listarTopicoID(@RequestBody @PathVariable @Valid Long id) {
        var response = topicoService.listarTopicoID(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "actualiza un topico en la base de datos")
    public ResponseEntity actualizarTopico(@RequestBody @Valid ActualizarTopico topico) {
        var response = topicoService.actualizarTopico(topico);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "elimina un topico en la base de datos")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }


}
