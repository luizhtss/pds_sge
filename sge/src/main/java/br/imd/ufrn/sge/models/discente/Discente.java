package br.imd.ufrn.sge.models.discente;

import br.imd.ufrn.sge.models.DadosPessoais;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discente", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_dados_pessoais", nullable = false)
    private DadosPessoais dadosPessoais;


    /**
     * Matrículas do discente
     * 1 discente pode ter várias matrículas.
     */
    @OneToMany(mappedBy = "discente")
    private List<MatriculaDiscente> matriculaDiscente;


}
