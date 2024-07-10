package Desafio_ForoHub.ForoHub.domain.curso;

import java.util.List;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        Categoria categoria,
        List<DatosTopicoCurso> topicos
) {
    public DatosDetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria(),
                curso.getTopicos().stream().map(DatosTopicoCurso::new).toList()
        );
    }
}
