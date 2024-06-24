package br.imd.ufrn.sge.framework.notas;

public class NotaNormalStrategy implements INotaStrategy {
    @Override
    public float calcularMedia(float unidade1, float unidade2, float unidade3) {
        return (unidade1 + unidade2 + unidade3) / 3;
    }
}