package br.imd.ufrn.sge.framework.frequencia;

import br.imd.ufrn.sge.models.Frequencia;

import java.util.List;

public class FrequenciaDias implements IFrequenciaStrategy{
    @Override
    public double calculateAttendancePercentage(List<Frequencia> frequencias) {
        int total = frequencias.size();
        int presencas = 0;
        for (Frequencia frequencia : frequencias) {
            if (frequencia.isPresenca()) {
                presencas++;
            }
        }
        return (double) presencas / total;
    }
}
