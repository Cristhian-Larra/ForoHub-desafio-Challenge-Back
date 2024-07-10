package Desafio_ForoHub.ForoHub.domain.curso;

import Desafio_ForoHub.ForoHub.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Boolean activo;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    public Curso() {
    }
    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
        this.activo = true;
        this.topicos = new ArrayList<>();
    }


    public void actualizar(ActualizarCurso datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
    }
}
