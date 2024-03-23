package br.imd.ufrn.sge.models.discente;

import jakarta.persistence.*;

@Entity
public class MatriculaDiscente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula_discente", nullable = false)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * Discente que está matriculado.
     * Varias matrículas podem ter o mesmo discente.
     */
    @ManyToOne
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;


    private void setId(Long id) {
        this.id = id;
    }

    private Long getId() {
        return id;
    }

    private void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    private String getMatricula() {
        return matricula;
    }

    private void setDiscente(Discente discente) {
        this.discente = discente;
    }

    private Discente getDiscente() {
        return discente;
    }

    public MatriculaDiscente() {
    }
}
