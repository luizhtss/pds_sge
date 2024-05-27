package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.docente.Docente;
import jakarta.persistence.*;

@Entity
public class ObservacaoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_matricula_discente")
    private MatriculaDiscente matriculaDiscente;

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = true)
    private Docente docenteResponsavel;

    public Long getId() {
        return id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public MatriculaDiscente getMatriculaDiscente() {
        return matriculaDiscente;
    }

    public Docente getDocenteResponsavel() {
        return docenteResponsavel;
    }

    public void setDocenteResponsavel(Docente docenteResponsavel) {
        this.docenteResponsavel = docenteResponsavel;
    }

    public void setMatriculaDiscente(MatriculaDiscente matriculaDiscente) {
        this.matriculaDiscente = matriculaDiscente;
    }




}
