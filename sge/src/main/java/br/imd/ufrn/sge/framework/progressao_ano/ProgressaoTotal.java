package br.imd.ufrn.sge.framework.progressao_ano;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProgressaoTotal")
public class ProgressaoTotal implements IProgressaoStrategy {
    @Override
    public void aprovaAno(MatriculaDiscente matriculaDiscente, List<Boolean> materiasStatus) {
        boolean statusFinal = true;
        for (Boolean status : materiasStatus) {
            if (!status)
                statusFinal = false;
        }
        matriculaDiscente.setStatus(statusFinal ? MatriculaDiscente.Status.APROVADO : MatriculaDiscente.Status.REPROVADO);
    }
}
