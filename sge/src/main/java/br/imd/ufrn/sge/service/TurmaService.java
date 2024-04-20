package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.relatorio.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional
    public Turma salvar(Turma t) {
        return turmaRepository.save(t);
    }

}
