package br.imd.ufrn.sge.framework.frequencia;

import br.imd.ufrn.sge.models.Frequencia;
import java.util.List;


public class FrequenciaHoras implements IFrequenciaStrategy{
    @Override
    public double calculateAttendancePercentage(List<Frequencia> frequencias) {
        int totalMinutes = frequencias.size() * 40;
        int attendedMinutes = 0;
        for (Frequencia frequencia : frequencias) {
            if (frequencia.isPresenca()) {
                attendedMinutes += frequencia.getTempoAulaMinutos();
            }
        }
        return (double) attendedMinutes / totalMinutes;
    }
}
