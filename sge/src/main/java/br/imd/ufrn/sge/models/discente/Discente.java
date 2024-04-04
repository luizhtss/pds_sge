package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DadosPessoais;
import jakarta.persistence.*;
import br.imd.ufrn.sge.models.responsavel.Responsavel;

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


    /**
     * Matrículas do discente
     * 1 discente pode ter várias matrículas.
     */
    @OneToMany(mappedBy = "discente")
    private List<MatriculaDiscente> matriculaDiscente;

    /**
     * Responsável pelo discente.
     */
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
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

    public List<MatriculaDiscente> getMatriculaDiscente() {
        return matriculaDiscente;
    }

    public void setMatriculaDiscente(List<MatriculaDiscente> matriculaDiscente) {
        this.matriculaDiscente = matriculaDiscente;
    }
}
