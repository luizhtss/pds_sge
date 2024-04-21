package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.turma.Turma;
import jakarta.persistence.*;

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

    public enum Status {
        MATRICULADO,
        APROVADO,
        REPROVADO
    }

    @Column(name = "ano", nullable = false)
    private int ano;

    @ManyToMany(mappedBy = "discentes")
    private Set<Turma> turmas;

    @OneToMany(mappedBy = "matricula_discente")
    private Set<DiscenteMateria> discenteMaterias;


    public MatriculaDiscente() {

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

    public Set<Turma> getTurmas() {
        return turmas;
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
