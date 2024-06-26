package br.imd.ufrn.sge.relatorio.data;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.interfaces.IDataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DadosObservacaoFetcher implements IDataFetcher {

        public String fetchData(MatriculaDiscente matriculaDiscente){
            String dataHardCoded = "Observacao: O aluno esta indo bem em todas as materias, porem precisa melhorar em portugues e biologia \n";
            dataHardCoded += "Pouca interacao com os colegas, o aluno precisa melhorar nesse aspecto ou uma investigação mais profunda.\n";
            dataHardCoded += "O aluno esta com um bom desempenho escolar, porem precisa melhorar a frequencia nas aulas\n";
            return dataHardCoded;
        }

}
