package br.imd.ufrn.sge.framework.aprovacao_ano;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

import java.util.List;

public interface IProgressaoStrategy {
    void aprovaAno(MatriculaDiscente matriculaDiscente, List<Boolean> materiasStatus);
}