package br.imd.ufrn.sge.models.responsavel;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.discente.Discente;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_responsavel", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;

    /**
     * Discentes dos quais este responsável é responsável.
     * Um responsável pode ser responsável por vários discentes.
     */
    @OneToMany
    @JoinColumn(name = "id_responsavel") // Esta coluna deve existir em Discente para referenciar o responsável.
    private List<Discente> discentes;

    // Getters e Setters
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

    public List<Discente> getDiscentes() {
        return discentes;
    }

    public void setDiscentes(List<Discente> discentes) {
        this.discentes = discentes;
    }

}