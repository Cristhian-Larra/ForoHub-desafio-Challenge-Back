package Desafio_ForoHub.ForoHub.controller;

import Desafio_ForoHub.ForoHub.domain.usuario.*;
import Desafio_ForoHub.ForoHub.infra.security.DatosJWTToken;
import Desafio_ForoHub.ForoHub.infra.security.TokenService;
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
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalladaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        var response = usuarioService.registrarUsuario(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal()) ;
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalladaUsuario>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        var response = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUsuarioID(@RequestBody @Valid @PathVariable Long id) {
        var response = usuarioService.listarUsuarioID(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarUsuario datos) {
        var response = usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
