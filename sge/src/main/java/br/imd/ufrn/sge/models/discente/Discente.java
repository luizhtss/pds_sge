package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.models.turma.Turma;
import jakarta.persistence.*;
import br.imd.ufrn.sge.models.responsavel.Responsavel;

import java.util.List;
import java.util.Set;

@Entity
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_discente")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;

    /**
     * Turma do discente.
     */
    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = true)
    private Turma turma;


    /**
     * Matrículas do discente.
     */

    @OneToMany(mappedBy = "discente", cascade = CascadeType.ALL)
    private List<MatriculaDiscente> matriculaDiscente;


    /**
     * Responsável pelo discente.
     */
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = true)
    private Responsavel responsavel;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    /*public List<MatriculaDiscente> getMatriculaDiscente() {
        return matriculaDiscente;
    }*/

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public static class Builder {
        private DadosPessoais dadosPessoais;

        private Responsavel responsavel;

        public Builder() {}

        public Builder withDadosPessoais(DadosPessoais dadosPessoais) {
            this.dadosPessoais = dadosPessoais;
            return this;
        }

        public Builder withResponsavel(Responsavel responsavel) {
            this.responsavel = responsavel;
            return this;
        }

        public Discente build() {
            Discente discente = new Discente();
            discente.dadosPessoais = this.dadosPessoais;
            discente.responsavel = this.responsavel;
            return discente;
        }
    }
}
