package br.imd.ufrn.sge.models.materia;

import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.models.turma.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_materia", nullable = false)
    private Long id_materia;

    @Column(name = "nome_materia")
    private String nome_materia;

    /**
     * Professor da matéria
     */
    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = true)
    private Docente docente;

    @Column(name = "descricao")
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    @JsonBackReference
    private Turma turma;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    public Long getId() {
        return id_materia;
    }

    public void setId(Long id) {
        this.id_materia= id;
    }

    public String getNome() {
        return nome_materia;
    }

    public void setNome(String nome) {
        this.nome_materia= nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

}