package Desafio_ForoHub.ForoHub.domain.respuesta;

import Desafio_ForoHub.ForoHub.domain.topico.Topico;
import Desafio_ForoHub.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topico topico;


    private LocalDateTime fecha_creacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Usuario usuario;

    private boolean solucion;

    private boolean activo;

    public Respuesta() {
    }

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.usuario = usuario;
        this.topico = topico;
        this.solucion = false;
        this.activo = true;

    }

    public void actualizar(ActualizarRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        fecha_creacion = LocalDateTime.now();
    }
}
