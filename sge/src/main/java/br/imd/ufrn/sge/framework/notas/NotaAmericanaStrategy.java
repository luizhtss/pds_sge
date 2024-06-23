package br.imd.ufrn.sge.framework.notas;

public class NotaAmericanaStrategy implements INotaStrategy {
    @Override
    public float calcularMedia(float unidade1, float unidade2, float unidade3) {
        float media = (unidade1 + unidade2 + unidade3) / 3;
        if (media >= 9) return 4.0f; // A
        else if (media >= 8) return 3.0f; // B
        else if (media >= 7) return 2.0f; // C
        else if (media >= 6) return 1.0f; // D
        else return 0.0f; // F
    }
}