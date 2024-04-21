package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.models.responsavel.Responsavel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_discente", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = true)
    private Turma turma;

    @OneToMany(mappedBy = "discente")
    private List<MatriculaDiscente> matriculaDiscente;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = true)
    private Responsavel responsavel;


    public Discente() {

    }

    public Long getId() {
        return id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public Turma getTurma() {
        return turma;
    }

    public List<MatriculaDiscente> getMatriculaDiscente() {
        return matriculaDiscente;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public static class Builder {
        private DadosPessoais dadosPessoais;
        private Turma turma;
        private List<MatriculaDiscente> matriculaDiscente;
        private Responsavel responsavel;

        public Builder() {}

        public Builder withDadosPessoais(DadosPessoais dadosPessoais) {
            this.dadosPessoais = dadosPessoais;
            return this;
        }

        public Builder withTurma(Turma turma) {
            this.turma = turma;
            return this;
        }

        public Builder withMatriculaDiscente(List<MatriculaDiscente> matriculaDiscente) {
            this.matriculaDiscente = matriculaDiscente;
            return this;
        }

        public Builder withResponsavel(Responsavel responsavel) {
            this.responsavel = responsavel;
            return this;
        }

        public Discente build() {
            Discente discente = new Discente();
            discente.dadosPessoais = this.dadosPessoais;
            discente.turma = this.turma;
            discente.matriculaDiscente = this.matriculaDiscente;
            discente.responsavel = this.responsavel;
            return discente;
        }
    }
}
