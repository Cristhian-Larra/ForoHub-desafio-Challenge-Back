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
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }

    public DatosDetalleTopico listarTopicoID(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow();
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizarTopico(ActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(datos.id());
        if (datos.respuestas() != null) {
            for (ActualizarRespuestaTopico actualizarRespuesta : datos.respuestas()) {
                Respuesta respuesta = respuestaRepository.findById(actualizarRespuesta.idRespuesta()).orElseThrow();
                if(actualizarRespuesta.idRespuesta() != null){
                    respuesta.setSolucion(actualizarRespuesta.correcta());
                }
            }
        }
        topico.actualizar(datos);
        DatosDetalleTopico datosRespuestaTopico = new DatosDetalleTopico(topico);
        return datosRespuestaTopico;
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow();
        topicoRepository.delete(topico);
    }



}
