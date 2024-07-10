package Desafio_ForoHub.ForoHub.domain.curso;

import Desafio_ForoHub.ForoHub.domain.topico.Topico;

public record DatosTopicoCurso(
        Long id,
        String titulo,
        String mensaje,
        String fecha,
        String autor
) {
    public DatosTopicoCurso(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion().toString(),
                topico.getUsuario().getNombre());
    }
}
