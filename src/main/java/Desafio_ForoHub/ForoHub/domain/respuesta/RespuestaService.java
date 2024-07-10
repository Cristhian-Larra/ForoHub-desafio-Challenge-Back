package Desafio_ForoHub.ForoHub.domain.respuesta;

import Desafio_ForoHub.ForoHub.domain.topico.TopicoRepository;
import Desafio_ForoHub.ForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestasRepository respuestasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleRespuesta registrarRespuesta(DatosRegistroRespuesta datos) {
        if (!usuarioRepository.findById(datos.idUsuario()).isPresent()){
            throw new IllegalArgumentException("No se encontro el usuario");
        }
        if (!topicoRepository.findById(datos.idTopico()).isPresent()){
            throw new IllegalArgumentException("No se encontro el topico");
        }
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        Respuesta respuesta = new Respuesta(datos, usuario, topico);
        respuestasRepository.save(respuesta);
        DatosDetalleRespuesta respuestaCreada = new DatosDetalleRespuesta(respuesta);

        return respuestaCreada;
    }

    public Page<DatosDetalleRespuesta> listarRespuestas(Pageable paginacion) {
        if (respuestasRepository.count() == 0) {
            throw new IllegalArgumentException("No hay respuestas registradas");
        }
        return respuestasRepository.findAll(paginacion).map(DatosDetalleRespuesta::new);
    }

    public DatosDetalleRespuesta listarRespuestaID(Long id) {
        if (!respuestasRepository.existsById(id)) {
            throw new IllegalArgumentException("Respuesta no encontrada");
        }
        Respuesta respuesta = respuestasRepository.findById(id).orElseThrow();
        return new DatosDetalleRespuesta(respuesta);
    }

    public DatosDetalleRespuesta actualizarRespuesta(ActualizarRespuesta datos) {
        if (!respuestasRepository.existsById(datos.id())) {
            throw new IllegalArgumentException("Respuesta no encontrada");
        }
        Respuesta respuesta = respuestasRepository.getReferenceById(datos.id());
        respuesta.actualizar(datos);
        return new DatosDetalleRespuesta(respuesta);
    }

    public void eliminarRespuesta(Long id) {
        if (!respuestasRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Respuesta no existe");
        }
        respuestasRepository.deleteById(id);
    }
}
