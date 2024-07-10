package Desafio_ForoHub.ForoHub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("""
            SELECT c
            FROM Curso c
            WHERE c.nombre = :nombre""")
    Curso findByNombre(String nombre);

}
