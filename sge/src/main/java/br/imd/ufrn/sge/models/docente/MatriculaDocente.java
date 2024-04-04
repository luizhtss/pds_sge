package br.imd.ufrn.sge.models.docente;

import jakarta.persistence.*;

@Entity
public class MatriculaDocente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matricula_docente", nullable = false)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * Docente que está matriculado.
     * Varias matrículas podem ter o mesmo docente.
     */
    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente MatriculaDocente;

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
        this.MatriculaDocente = docente;
    }

    public Docente getDocente() {
        return MatriculaDocente;
    }

    public MatriculaDocente() {
    }
}
