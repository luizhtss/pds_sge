package br.imd.ufrn.sge.relatorio.relatorio;


import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "tipo_relatorio")
public abstract class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "texto")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "id_matricula_discente", nullable = false)
    private MatriculaDiscente matriculaDiscente;

    public MatriculaDiscente getMatriculaDiscente() {
        return matriculaDiscente;
    }

    public void setMatriculaDiscente(MatriculaDiscente matriculaDiscente) {
        this.matriculaDiscente = matriculaDiscente;
    }

    @Column(name = "melhorado_por_ia", insertable = false, updatable = false)
    private boolean enchancedByAI;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEnchancedByAI() {
        return enchancedByAI;
    }
    public void setEnchancedByAI(boolean enchancedByAI) {
        this.enchancedByAI = enchancedByAI;
    }
}
