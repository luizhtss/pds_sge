package br.imd.ufrn.sge.service.associacao;

import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoTurmaService {

    @Autowired
    TurmaService turmaService;

    public void associarDiscenteTurma(Long idDiscente, Long idTurma) {

    }
}
