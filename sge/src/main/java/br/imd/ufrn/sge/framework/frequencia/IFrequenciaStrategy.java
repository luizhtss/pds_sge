package br.imd.ufrn.sge.framework.frequencia;

import br.imd.ufrn.sge.models.Frequencia;

import java.util.List;

public interface IFrequenciaStrategy {
    double calculateAttendancePercentage(List<Frequencia> frequencias);
}

