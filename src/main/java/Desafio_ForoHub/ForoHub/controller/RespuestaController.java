package Desafio_ForoHub.ForoHub.controller;

import Desafio_ForoHub.ForoHub.domain.respuesta.*;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                             UriComponentsBuilder uriComponentsBuilder) {
        var response = respuestaService.registrarRespuesta(datos);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas( @PageableDefault(size = 10) Pageable paginacion) {
        var response = respuestaService.listarRespuestas(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarRespuestaID(@RequestBody @Valid @PathVariable Long id) {
        var response = respuestaService.listarRespuestaID(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid ActualizarRespuesta datos) {
        var response = respuestaService.actualizarRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
