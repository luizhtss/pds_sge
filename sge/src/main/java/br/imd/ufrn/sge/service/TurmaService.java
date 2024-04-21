package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> encontrarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public List<Turma> findByName(String name) {
        return turmaRepository.findByName(name);
    }

    @Transactional
    public Turma salvar(Turma t) {
        return turmaRepository.save(t);
    }

    @Transactional
    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }

    public boolean isDiscenteMatriculado(Long idDiscente, Long idTurma) {
        return turmaRepository.isDiscenteMatriculado(idDiscente, idTurma);
    }

    public void matricularDiscente(Long idMatriculaDiscente, Long idTurma) {
        turmaRepository.matricularDiscente(idMatriculaDiscente, idTurma);
    }
}
