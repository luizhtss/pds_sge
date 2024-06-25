package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.turma.Turma;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class MatriculaDiscente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matricula_discente", nullable = false)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.MATRICULADO;

    @OneToMany(mappedBy = "matricula_discente", cascade = CascadeType.ALL)
    private Set<DiscenteMateria> discenteMaterias;


    @OneToMany(mappedBy = "matriculaDiscente", cascade = CascadeType.DETACH)
    private List<ObservacaoDiscente> observacoes;

    public enum Status {
        MATRICULADO,
        APROVADO,
        REPROVADO,
        PROGRESSAO_PARCIAL
    }

    @Column(name = "ano", nullable = false)
    private int ano;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public Discente getDiscente() {
        return discente;
    }

    public int getAno() {
        return ano;
    }

    public Set<DiscenteMateria> getDiscenteMaterias() {
        return discenteMaterias;
    }

    public List<ObservacaoDiscente> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(List<ObservacaoDiscente> observacoes) {
        this.observacoes = observacoes;
    }

    public static class Builder {
        private String matricula;
        private Discente discente;
        private int ano;

        public Builder(String matricula, Discente discente) {
            this.matricula = matricula;
            this.discente = discente;
        }

        public Builder() {

        }

        public Builder withMatricula(String matricula) {
            this.matricula = matricula;
            return this;
        }

        public Builder withDiscente(Discente discente) {
            this.discente = discente;
            return this;
        }

        public Builder withAno(int ano) {
            this.ano = ano;
            return this;
        }

        public MatriculaDiscente build() {
            MatriculaDiscente matriculaDiscente = new MatriculaDiscente();
            matriculaDiscente.matricula = this.matricula;
            matriculaDiscente.discente = this.discente;
            matriculaDiscente.ano = this.ano;
            return matriculaDiscente;
        }
    }
}
