package br.imd.ufrn.sge.framework.progressao_ano;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExecuteProgressaoStrategy {
    private Map<String, IProgressaoStrategy> strategies;

    public ExecuteProgressaoStrategy(Map<String, IProgressaoStrategy> strategies) {
        this.strategies = strategies;
    }

    public void executeStrategy(String strategy, MatriculaDiscente matriculaDiscente, List<Boolean> materiasStatus) {
        if (!strategies.containsKey(strategy)) {
            throw new IllegalArgumentException("The strategy " + strategy + " does not exist.");
        }
        strategies.get(strategy).aprovaAno(matriculaDiscente, materiasStatus);
    }
}
