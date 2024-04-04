package br.imd.ufrn.sge.models.materia;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.models.discente.Discente;
import jakarta.persistence.*;
@Entity
public class NotasAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_notas_aluno", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_discente", nullable = false)
    private Discente discente;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @Column(name = "unidade_1")
    private float unidade1;

    @Column(name = "unidade_2")
    private float unidade2;

    @Column(name = "unidade_3")
    private float unidade3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getUnidade1() {
        return unidade1;
    }

    public void setUnidade1(float unidade1) {
        this.unidade1 = unidade1;
    }

    public float getUnidade2() {
        return unidade2;
    }

    public void setUnidade2(float unidade2) {
        this.unidade2 = unidade2;
    }

    public float getUnidade3() {
        return unidade3;
    }

    public void setUnidade3(float unidade3) {
        this.unidade3 = unidade3;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
