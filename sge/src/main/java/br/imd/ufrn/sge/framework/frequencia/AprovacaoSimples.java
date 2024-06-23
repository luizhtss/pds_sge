package br.imd.ufrn.sge.framework.frequencia;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.Frequencia;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

import java.util.List;

public class AprovacaoSimples extends AprovacaoTemplate {


    public double calculaMedia(double u1, double u2, double u3) {
        return 0;
    }

    @Override
    public void aprovaAluno(DiscenteMateria discenteMateria, List<Frequencia> frequencias) {
        double u1 = discenteMateria.getUnidade1();
        double u2 = discenteMateria.getUnidade2();
        double u3 = discenteMateria.getUnidade3();
        double pf = discenteMateria.getProvaFinal();
        double media = calculaMedia(u1, u2, u3);

        double frequencia = calculaFrequencia(frequencias);

        if(media < 60) {
            media = (media + pf) / 2;
        }

        if(media >= 60 && frequencia >= 75) {
            discenteMateria.setStatus(MatriculaDiscente.Status.APROVADO);
        } else {
            discenteMateria.setStatus(MatriculaDiscente.Status.REPROVADO);
        }
    }
}
