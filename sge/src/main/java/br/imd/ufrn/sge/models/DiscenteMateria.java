package br.imd.ufrn.sge.models;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.materia.Materia;
import jakarta.persistence.*;
@Entity
public class DiscenteMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_discente_materia", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_discente", nullable = false)
    private MatriculaDiscente matricula_discente;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @Column(name = "unidade_1")
    private Float unidade1;

    @Column(name = "unidade_2")
    private Float unidade2;

    @Column(name = "unidade_3")
    private Float unidade3;

    public Integer getPresenca() {
        return presenca;
    }

    public void setPresenca(Integer presenca) {
        this.presenca = presenca;
    }

    @Column(name = "presenca")
    private Integer presenca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getUnidade1() {
        return unidade1;
    }

    public void setUnidade1(Float unidade1) {
        this.unidade1 = unidade1;
    }

    public Float getUnidade2() {
        return unidade2;
    }

    public void setUnidade2(Float unidade2) {
        this.unidade2 = unidade2;
    }

    public Float getUnidade3() {
        return unidade3;
    }

    public void setUnidade3(Float unidade3) {
        this.unidade3 = unidade3;
    }

    public MatriculaDiscente getDiscente() {
        return matricula_discente;
    }

    public void setDiscente(MatriculaDiscente matricula_discente) {
        this.matricula_discente = matricula_discente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
