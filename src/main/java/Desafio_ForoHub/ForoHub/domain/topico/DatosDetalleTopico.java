package Desafio_ForoHub.ForoHub.domain.topico;

import Desafio_ForoHub.ForoHub.domain.respuesta.DatosDetalleRespuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        String curso,
        List<DatosRespuestaTopico> respuesta

) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(),
                topico.getUsuario().getNombre(),topico.getCurso().getNombre(),
                topico.getRespuestas().stream().map(DatosRespuestaTopico::new).toList());
    }
}
