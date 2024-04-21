package br.imd.ufrn.sge.models.docente;

import br.imd.ufrn.sge.models.turma.Turma;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MatriculaDocente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matricula_docente", nullable = false)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * Discente que está matriculado.
     * Um discente pode ter várias matrículas.
     */
    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente matriculaDocente;


    @ManyToMany
    @JoinTable(
            name = "DocenteTurma",
            joinColumns = @JoinColumn(name = "id_docente"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private Set<Turma> turmas;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setDocente(Docente docente) {
        this.matriculaDocente = docente;
    }

    public Docente getDocente() {
        return matriculaDocente;
    }

    public MatriculaDocente() {
    }
}
