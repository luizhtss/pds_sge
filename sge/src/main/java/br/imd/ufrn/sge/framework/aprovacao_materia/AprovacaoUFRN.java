package br.imd.ufrn.sge.framework.aprovacao_materia;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.Frequencia;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

import java.util.List;

public class AprovacaoUFRN extends AprovacaoTemplate {

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
        double menorNota = Math.min(u1, Math.min(u2, u3));

        double frequencia = calculaFrequencia(frequencias);

        if((menorNota < 4 || media < 6) && pf > menorNota) {
            if(menorNota == u1) {
                u1 = pf;
            } else if(menorNota == u2) {
                u2 = pf;
            } else {
                u3 = pf;
            }
            media = calculaMedia(u1, u2, u3);
            if(media >= 50 && frequencia >= 75) {
                discenteMateria.setStatus(MatriculaDiscente.Status.APROVADO);
            } else {
                discenteMateria.setStatus(MatriculaDiscente.Status.REPROVADO);
            }
        } else  if(media >= 60 && frequencia >= 75) {
            discenteMateria.setStatus(MatriculaDiscente.Status.APROVADO);
        } else {
            discenteMateria.setStatus(MatriculaDiscente.Status.REPROVADO);
        }
    }
}
