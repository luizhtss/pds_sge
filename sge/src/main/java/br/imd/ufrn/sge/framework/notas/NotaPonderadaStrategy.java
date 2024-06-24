package br.imd.ufrn.sge.framework.notas;

public class NotaPonderadaStrategy implements INotaStrategy {
    @Override
    public float calcularMedia(float unidade1, float unidade2, float unidade3) {
        return (unidade1 * 0.3f + unidade2 * 0.3f + unidade3 * 0.4f);
    }
}