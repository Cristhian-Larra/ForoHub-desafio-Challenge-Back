package Desafio_ForoHub.ForoHub.controller;

import Desafio_ForoHub.ForoHub.domain.usuario.*;
import Desafio_ForoHub.ForoHub.infra.security.DatosJWTToken;
import Desafio_ForoHub.ForoHub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra un usuario en la base de datos")
    public ResponseEntity<DatosDetalladaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        var response = usuarioService.registrarUsuario(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "autentica un usuario en la base de datos")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal()) ;
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de usuarios")
    public ResponseEntity<Page<DatosDetalladaUsuario>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        var response = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un usuario por ID")
    public ResponseEntity listarUsuarioID(@RequestBody @Valid @PathVariable Long id) {
        var response = usuarioService.listarUsuarioID(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    @Transactional
    @Operation(
            summary = "actualiza un usuario en la base de datos")
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarUsuario datos) {
        var response = usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "elimina un usuario en la base de datos")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
