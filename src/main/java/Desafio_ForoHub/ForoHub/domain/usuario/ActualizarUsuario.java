package Desafio_ForoHub.ForoHub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(
        @NotNull
        Long id,
        String nombre
)
{
}
