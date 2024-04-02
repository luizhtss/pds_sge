package br.imd.ufrn.sge.relatorio.data;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.interfaces.IDataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DadosAcademicoFetcher implements IDataFetcher {

    // Por hora, hardcoded.
    public String fetchData(MatriculaDiscente matriculaDiscente){
        String dataHardCoded = "Materia: Ciencias. Nota: 10.0\nMateria: Matematica. Nota: 9.0\nMateria: Historia. Nota: 8.0\nMateria: Geografia. Nota: 7.0\nMateria: Portugues. Nota: 6.0\nMateria: Ingles. Nota: 5.0\nMateria: Espanhol. Nota: 4.0\nMateria: Fisica. Nota: 3.0\nMateria: Quimica. Nota: 2.0\nMateria: Biologia. Nota: 1.0";
        return dataHardCoded;
    }

}
