package Desafio_ForoHub.ForoHub.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso registrarCurso(DatosRegistroCurso datosRegistroCurso) {
        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        DatosDetalleCurso detalleCurso = new DatosDetalleCurso(curso);
        return detalleCurso;
    }

    public Page<DatosDetalleCurso> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosDetalleCurso::new);
    }

    public DatosDetalleCurso listarCursoID(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        return new DatosDetalleCurso(curso);
    }

    public DatosDetalleCurso actualizarCurso(ActualizarCurso datos) {
        Curso curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DatosDetalleCurso(curso);
    }

    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        cursoRepository.delete(curso);
    }

}
