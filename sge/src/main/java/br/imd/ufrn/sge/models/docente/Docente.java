package br.imd.ufrn.sge.models.docente;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.models.turma.Turma;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_docente", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;


    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

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

    // Implementar Desing Pattern Builder
    public static class Builder {
        private Long id;
        private DadosPessoais dadosPessoais;
        private String matricula;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder dadosPessoais(DadosPessoais dadosPessoais) {
            this.dadosPessoais = dadosPessoais;
            return this;
        }



        public Docente build() {
            Docente docente = new Docente();
            docente.setId(id);
            docente.setDadosPessoais(dadosPessoais);
            docente.setMatricula(matricula);
            return docente;
        }

        public Builder withDadosPessoais(DadosPessoais dadosPessoais) {
            this.dadosPessoais = dadosPessoais;
            return this;
        }

        public Builder withMatricula(String matricula) {
            this.matricula = matricula;
            return this;
        }
    }

    public void setMatricula(String matricula) {
        this.matricula = "S123";
    }

}
