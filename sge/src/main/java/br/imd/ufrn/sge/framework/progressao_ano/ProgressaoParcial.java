package br.imd.ufrn.sge.framework.aprovacao_ano;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

import java.util.List;

public class ProgressaoParcial implements IProgressaoStrategy {
    @Override
    public void aprovaAno(MatriculaDiscente matriculaDiscente, List<Boolean> materiasStatus) {
        int reprovadas = 0;
        for (Boolean status : materiasStatus) {
            if (!status)
                reprovadas++;
        }
        if (reprovadas > materiasStatus.size() / 2)
            matriculaDiscente.setStatus(MatriculaDiscente.Status.REPROVADO);
        else if (reprovadas < materiasStatus.size() / 2 && reprovadas > 0)
            matriculaDiscente.setStatus(MatriculaDiscente.Status.PROGRESSAO_PARCIAL);
        else
            matriculaDiscente.setStatus(MatriculaDiscente.Status.APROVADO);
    }
}