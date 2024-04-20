package br.imd.ufrn.sge.models.docente;

import br.imd.ufrn.sge.models.DadosPessoais;
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

    /**
     * Matrículas do docente
     * 1 docente pode ter várias matrículas.
     */
    @OneToMany(mappedBy = "docente")
    private List<MatriculaDocente> matriculaDocente;

    /**
     * Definindo junção de docente com turmas
     * */
    @ManyToMany
    @JoinTable(
            name = "DocenteTurma",
            joinColumns = @JoinColumn(name = "id_docente"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    Set<Turma> turmas;


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
