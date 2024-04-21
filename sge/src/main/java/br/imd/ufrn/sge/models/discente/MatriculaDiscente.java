package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DadosPessoais;
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


    @Column(name = "ano", nullable = false)
    private int ano;

    @ManyToMany
    @JoinTable(
            name = "DocenteTurma",
            joinColumns = @JoinColumn(name = "id_discente"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private Set<Turma> turmas;


    public MatriculaDiscente() {

    }

    // Getter para o ID
    public Long getId() {
        return id;
    }

    // Getter para a matrícula
    public String getMatricula() {
        return matricula;
    }

    // Getter para o discente
    public Discente getDiscente() {
        return discente;
    }

    // Getter para o ano
    public int getAno() {
        return ano;
    }

    // Classe Builder estática dentro da classe MatriculaDiscente
    public static class Builder {
        private String matricula;
        private Discente discente;
        private int ano;

        // Construtor do Builder com os campos obrigatórios
        public Builder(String matricula, Discente discente) {
            this.matricula = matricula;
            this.discente = discente;
        }

        public Builder() {

        }

        // Método para configurar a matrícula
        public Builder withMatricula(String matricula) {
            this.matricula = matricula;
            return this;
        }

        // Método para configurar o discente
        public Builder withDiscente(Discente discente) {
            this.discente = discente;
            return this;
        }

        // Método para configurar o ano
        public Builder withAno(int ano) {
            this.ano = ano;
            return this;
        }

        // Método para construir uma instância de MatriculaDiscente
        public MatriculaDiscente build() {
            MatriculaDiscente matriculaDiscente = new MatriculaDiscente();
            matriculaDiscente.matricula = this.matricula;
            matriculaDiscente.discente = this.discente;
            return matriculaDiscente;
        }
    }
}
