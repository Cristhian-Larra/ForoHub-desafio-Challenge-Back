package Desafio_ForoHub.ForoHub.domain.usuario;

import Desafio_ForoHub.ForoHub.domain.respuesta.Respuesta;

public record DatosRespuestaUsuario(
        Long id,
        String mensaje,
        Long topico
) {
    public DatosRespuestaUsuario(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId());
    }
}
