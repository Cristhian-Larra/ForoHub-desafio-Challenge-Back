package Desafio_ForoHub.ForoHub.controller;

import Desafio_ForoHub.ForoHub.domain.curso.ActualizarCurso;
import Desafio_ForoHub.ForoHub.domain.curso.CursoService;
import Desafio_ForoHub.ForoHub.domain.curso.DatosDetalleCurso;
import Desafio_ForoHub.ForoHub.domain.curso.DatosRegistroCurso;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra un curso en la base de datos")
    public ResponseEntity<DatosDetalleCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos,
                                         UriComponentsBuilder uriComponentsBuilder) {
        var response = cursoService.registrarCurso(datos);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<DatosDetalleCurso>> listarCursos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = cursoService.listarCursos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID")
    public ResponseEntity listarCursoID(@PathVariable Long id) {
        var response = cursoService.listarCursoID(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "actualiza un curso en la base de datos")
    public ResponseEntity actualizarCurso(@RequestBody @Valid ActualizarCurso datos) {
        var response = cursoService.actualizarCurso(datos);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "elimina un curso de la base de datos")
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

}
