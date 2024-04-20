package br.imd.ufrn.sge.models.turma;

import br.imd.ufrn.sge.models.docente.Docente;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_turma", nullable = false)
    private Long id;


    @ManyToMany(mappedBy = "turmas")
    private Set<Docente> docentes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}