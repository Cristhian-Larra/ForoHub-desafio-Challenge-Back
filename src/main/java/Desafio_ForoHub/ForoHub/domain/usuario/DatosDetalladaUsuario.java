package Desafio_ForoHub.ForoHub.domain.usuario;

import java.util.List;

public record DatosDetalladaUsuario(
        Long id,
        String nombre,
        String email,
        List<DatosTopicoUsuario> topicos ,
        List<DatosRespuestaUsuario> respuestas
) {
    public DatosDetalladaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                usuario.getTopicos().stream().map(DatosTopicoUsuario::new).toList(),
                usuario.getRespuestas().stream().map(DatosRespuestaUsuario::new).toList()
        );
    }
}
