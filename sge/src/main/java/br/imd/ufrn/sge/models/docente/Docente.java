package br.imd.ufrn.sge.models.docente;

import br.imd.ufrn.sge.models.DadosPessoais;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_docente", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;

    /**
     * Matrículas do docente
     * 1 docente pode ter várias matrículas.
     */
    @OneToMany(mappedBy = "docente")
    private List<MatriculaDocente> matriculaDocente;


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

    public List<MatriculaDocente> getMatriculaDocente() {
        return matriculaDocente;
    }

    public void setMatriculaDocente(List<MatriculaDocente> matriculaDocente) {
        this.matriculaDocente = matriculaDocente;
    }
}