package br.imd.ufrn.sge.models.discente;

import jakarta.persistence.*;

@Entity
public class MatriculaDiscente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matricula_discente", nullable = false)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * Discente que está matriculado.
     * Um discente pode ter várias matrículas.
     */
    @ManyToOne
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;

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

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Discente getDiscente() {
        return discente;
    }

    public MatriculaDiscente() {
    }
}
