package Desafio_ForoHub.ForoHub.domain.usuario;

import Desafio_ForoHub.ForoHub.domain.topico.Topico;

import java.util.List;
import java.util.stream.Collectors;

public record DatosTopicoUsuario(
        Long id,
        String titulo,
        String mensaje,
        List<Long> respuestas
) {

    public DatosTopicoUsuario(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getRespuestas().stream().map(respuesta -> respuesta.getId()).collect(Collectors.toList())
        );
    }
}
