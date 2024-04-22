package br.imd.ufrn.sge.relatorio.data;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.relatorio.interfaces.IDataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DadosAcademicoFetcher implements IDataFetcher {

    public String fetchData(MatriculaDiscente matriculaDiscente){
        StringBuilder dataDb = new StringBuilder();
        for (DiscenteMateria discenteMateria : matriculaDiscente.getDiscenteMaterias()) {
            Materia materia = discenteMateria.getMateria();
            float notaPrimeiraUnidade = discenteMateria.getUnidade1();
            float notaSegundaUnidade = discenteMateria.getUnidade2();
            float notaTerceiraUnidade = discenteMateria.getUnidade3();

            dataDb.append("Materia: ").append(materia.getNome()).append(". Notas: ");
            dataDb.append(String.format("%.2f", notaPrimeiraUnidade)).append(", ");
            dataDb.append(String.format("%.2f", notaSegundaUnidade)).append(", ");
            dataDb.append(String.format("%.2f", notaTerceiraUnidade)).append("\n");

        }
        //String dataHardCoded = "Materia: Ciencias. Nota: 10.0\nMateria: Matematica. Nota: 9.0\nMateria: Historia. Nota: 8.0\nMateria: Geografia. Nota: 7.0\nMateria: Portugues. Nota: 6.0\nMateria: Ingles. Nota: 5.0\nMateria: Espanhol. Nota: 4.0\nMateria: Fisica. Nota: 3.0\nMateria: Quimica. Nota: 2.0\nMateria: Biologia. Nota: 1.0";
        return dataDb.toString();
    }

}
