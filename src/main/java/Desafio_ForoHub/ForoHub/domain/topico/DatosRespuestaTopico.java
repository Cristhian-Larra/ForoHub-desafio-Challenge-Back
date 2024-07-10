package Desafio_ForoHub.ForoHub.domain.topico;

import Desafio_ForoHub.ForoHub.domain.respuesta.Respuesta;

public record DatosRespuestaTopico (
        String mensaje,
        String autor,
        String fecha,
        Boolean correcta
) {
    public DatosRespuestaTopico(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getUsuario().getNombre(), respuesta.getFecha_creacion().toString(),
                respuesta.isSolucion());
    }


}
