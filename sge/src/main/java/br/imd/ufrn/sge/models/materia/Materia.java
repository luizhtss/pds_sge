package br.imd.ufrn.sge.models.materia;

import jakarta.persistence.*;

@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_materia", nullable = false)
    private Long id;
    @Column(name = "nome_materia", nullable = false)
    private String nome;
    @Column(name = "descricao", nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}