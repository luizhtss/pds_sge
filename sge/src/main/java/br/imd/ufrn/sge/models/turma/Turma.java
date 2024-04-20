package br.imd.ufrn.sge.models.turma;

import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.models.docente.MatriculaDocente;
import br.imd.ufrn.sge.models.materia.Materia;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_turma", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "turmas")
    private Set<MatriculaDocente> docentes;

    /**
     * Definindo junção de turmas com materias
     * */
    @ManyToMany
    @JoinTable(
            name = "TurmaMateria",
            joinColumns = @JoinColumn(name = "id_turma"),
            inverseJoinColumns = @JoinColumn(name = "id_materia")
    )
    Set<Materia> materias;


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


}