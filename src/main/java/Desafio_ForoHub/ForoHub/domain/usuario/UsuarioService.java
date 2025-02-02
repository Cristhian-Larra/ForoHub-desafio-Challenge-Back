package Desafio_ForoHub.ForoHub.domain.usuario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public DatosDetalladaUsuario registrarUsuario(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new IllegalArgumentException("Usuario ya registrado");
        }
        Usuario usuario = new Usuario(datos);
        String password = encodePassword(datos.password());
        usuario.setPassword(password);
        System.out.println(password);
        usuarioRepository.save(usuario);
        DatosDetalladaUsuario DatosRespuestaUsuario = new DatosDetalladaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTopicos().stream().map(DatosTopicoUsuario::new).toList(),
                usuario.getRespuestas().stream().map(DatosRespuestaUsuario::new).toList()
                );
        return DatosRespuestaUsuario;
    }

    public Page<DatosDetalladaUsuario> listarUsuarios(Pageable pageable) {
        if (usuarioRepository.count() == 0) {
            throw new IllegalArgumentException("No hay usuarios registrados");
        }
        return usuarioRepository.findAll(pageable).map(DatosDetalladaUsuario::new);
    }

    public DatosDetalladaUsuario listarUsuarioID(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        DatosDetalladaUsuario datosRespuestaUsuario = new DatosDetalladaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTopicos().stream().map(DatosTopicoUsuario::new).toList(),
                usuario.getRespuestas().stream().map(DatosRespuestaUsuario::new).toList()
        );
        return datosRespuestaUsuario;
    }


    public DatosDetalladaUsuario actualizarUsuario(ActualizarUsuario datos) {
        if (!usuarioRepository.existsById(datos.id())) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        Usuario usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizar(datos);
        DatosDetalladaUsuario datosRespuestaUsuario = new DatosDetalladaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTopicos().stream().map(DatosTopicoUsuario::new).toList(),
                usuario.getRespuestas().stream().map(DatosRespuestaUsuario::new).toList()
        );
        return datosRespuestaUsuario;
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
