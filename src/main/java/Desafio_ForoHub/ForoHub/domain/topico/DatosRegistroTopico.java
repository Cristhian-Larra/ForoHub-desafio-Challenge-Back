package Desafio_ForoHub.ForoHub.domain.topico;

import Desafio_ForoHub.ForoHub.domain.curso.DatosRegistroCurso;
import Desafio_ForoHub.ForoHub.domain.usuario.DatosRegistroUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroTopico(
        @NotNull
        Long id_usuario,
        @NotBlank
        String titulo,
        @NotBlank
        String nombre_curso,
        @NotBlank
        String mensaje
) {
}
