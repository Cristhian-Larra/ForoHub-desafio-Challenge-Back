package Desafio_ForoHub.ForoHub.domain.respuesta;

public record DatosDetalleRespuesta(
        Long id,
        String topico,
        String mensaje,
        String autor,
        String fecha
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTopico().getTitulo().toString(), respuesta.getMensaje(), respuesta.getUsuario().getNombre(),
                respuesta.getFecha_creacion().toString());
    }
}
