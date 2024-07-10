package Desafio_ForoHub.ForoHub.domain.topico;

import Desafio_ForoHub.ForoHub.domain.curso.Curso;
import Desafio_ForoHub.ForoHub.domain.curso.CursoRepository;
import Desafio_ForoHub.ForoHub.domain.respuesta.Respuesta;
import Desafio_ForoHub.ForoHub.domain.respuesta.RespuestasRepository;
import Desafio_ForoHub.ForoHub.domain.usuario.Usuario;
import Desafio_ForoHub.ForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RespuestasRepository respuestaRepository;

    public DatosDetalleTopico registrarTopico(DatosRegistroTopico datos) {
        if (!usuarioRepository.findById(datos.id_usuario()).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (cursoRepository.findByNombre(datos.nombre_curso()) == null) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new IllegalArgumentException("Titulo del Topico ya fue registrado");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())) {
            throw new IllegalArgumentException("El mensaje del Topico ya fue registrada");
        }
        var usuario = usuarioRepository.getReferenceById(datos.id_usuario());
        var nombreCurso = cursoRepository.findByNombre(datos.nombre_curso());
        System.out.println(datos.nombre_curso());
        System.out.println("prueba");
        System.out.println(nombreCurso);
        Topico topico = new Topico(datos, usuario, nombreCurso);
        topicoRepository.save(topico);
        DatosDetalleTopico topicoCreado = new DatosDetalleTopico(topico);
        return topicoCreado;
    }


    public Page<DatosDetalleTopico> listarTopicos(Pageable paginacion) {
        if (topicoRepository.count() == 0) {
            throw new IllegalArgumentException("No hay topicos registrados");
        }
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }

    public DatosDetalleTopico listarTopicoID(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Topico no encontrado");
        }
        Topico topico = topicoRepository.findById(id).orElseThrow();
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizarTopico(ActualizarTopico datos) {
        if (!topicoRepository.existsById(datos.id())) {
            throw new IllegalArgumentException("Topico no encontrado");
        }
        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new IllegalArgumentException("Titulo del Topico ya esta registrado");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())) {
            throw new IllegalArgumentException("El mensaje del Topico ya esta registrada");
        }

        Topico topico = topicoRepository.getReferenceById(datos.id());
        if (topico.getRespuestas().isEmpty()){
            throw new IllegalArgumentException("El topico no tiene respuestas");
        }
        if (datos.respuestas() != null) {
            for (ActualizarRespuestaTopico actualizarRespuesta : datos.respuestas()) {
                Respuesta respuesta = respuestaRepository.findById(actualizarRespuesta.idRespuesta())
                        .orElseThrow(() -> new IllegalArgumentException("Respuesta no encontrada"));
                if (!topico.getRespuestas().contains(respuesta)) {
                    throw new IllegalArgumentException("La respuesta no pertenece a este tópico");
                }

            }
        }
        topico.actualizar(datos);
        DatosDetalleTopico datosRespuestaTopico = new DatosDetalleTopico(topico);
        return datosRespuestaTopico;
    }

    public void eliminarTopico(Long id) {
        if (!topicoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Topico no encontrado");
        }
        topicoRepository.deleteById(id);
    }



}
