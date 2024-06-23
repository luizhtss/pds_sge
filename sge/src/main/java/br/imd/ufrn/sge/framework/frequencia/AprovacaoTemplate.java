package br.imd.ufrn.sge.framework.frequencia;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.Frequencia;

import java.util.List;

public abstract class AprovacaoTemplate {

    public void aprovacaoMethod(DiscenteMateria discenteMateria, List<Frequencia> frequencias) {
        calculaFrequencia(frequencias);
        aprovaAluno(discenteMateria, frequencias);
    }

    public double calculaFrequencia(List<Frequencia> frequencias) {
        int totalAulas = frequencias.size();
        int totalPresencas = 0;
        for (Frequencia frequencia : frequencias) {
            if (frequencia.isPresenca()) {
                totalPresencas++;
            }
        }
        return (totalPresencas * 100) / totalAulas;
    }

    public abstract void aprovaAluno(DiscenteMateria discenteMateria, List<Frequencia> frequencias);
}