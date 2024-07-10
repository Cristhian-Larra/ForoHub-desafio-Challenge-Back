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
        if (cursoRepository.existsByNombre(datosRegistroCurso.nombre())) {
            throw new IllegalArgumentException("Curso ya registrado");
        }
        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        DatosDetalleCurso detalleCurso = new DatosDetalleCurso(curso);
        return detalleCurso;
    }

    public Page<DatosDetalleCurso> listarCursos(Pageable paginacion) {
        if (cursoRepository.count() == 0) {
            throw new IllegalArgumentException("No hay cursos registrados");
        }
        return cursoRepository.findAll(paginacion).map(DatosDetalleCurso::new);
    }

    public DatosDetalleCurso listarCursoID(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso no existe ");
        }
        Curso curso = cursoRepository.findById(id).orElseThrow();
        return new DatosDetalleCurso(curso);
    }

    public DatosDetalleCurso actualizarCurso(ActualizarCurso datos) {
        if (!cursoRepository.existsById(datos.id())) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        Curso curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DatosDetalleCurso(curso);
    }

    public void eliminarCurso(Long id) {
        if (!cursoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }

}
