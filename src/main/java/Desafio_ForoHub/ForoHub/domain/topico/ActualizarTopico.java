package Desafio_ForoHub.ForoHub.domain.topico;

import java.util.List;

public record ActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        List<ActualizarRespuestaTopico> respuestas
) {
}
