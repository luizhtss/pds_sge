package br.imd.ufrn.sge.framework.notas.strategy;

import br.imd.ufrn.sge.framework.notas.INotaStrategy;


public class NotaContext {
    private INotaStrategy notaStrategy;
    public void setNotaStrategy( INotaStrategy notaStrategy) {
        this.notaStrategy = notaStrategy;
    }

    public float calcularMedia(float unidade1, float unidade2, float unidade3){
        if (notaStrategy == null) {
            throw new IllegalStateException("Estratégia de nota não definida");
        }
        return notaStrategy.calcularMedia(unidade1, unidade2, unidade3);
    }
}
