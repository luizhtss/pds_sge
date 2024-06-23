package br.imd.ufrn.sge.models;
import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

import java.time.LocalDate;

import br.imd.ufrn.sge.models.docente.Docente;
import jakarta.persistence.*;

@Entity
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_frequencia", nullable = false)
    private Long id;

    @Column(name = "timestamp")
    private LocalDate data = LocalDate.now();

    @Column(name = "presenca")
    private boolean presenca;

    public boolean isPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }
}
