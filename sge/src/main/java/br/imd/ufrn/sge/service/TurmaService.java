package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.materia.Materia;
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

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Optional<Discente> listarMatriculados(Long idTurma) {
        return turmaRepository.findMatriculados(idTurma);
    }

    public Optional<Turma> encontrarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public List<Turma> findByName(String name) {
        return turmaRepository.findByName(name);
    }

    public Optional<Materia> findMateriasDocente(Long idDocente) {
        return turmaRepository.findMateriasDocente(idDocente);
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

    public Turma matricularDiscente(Long idMatriculaDiscente, Long idTurma) {
        Optional<Turma> turma = turmaRepository.findById(idTurma);
        if (turma.isPresent()){
            Optional<MatriculaDiscente> matriculaDiscente = matriculaDiscenteService.findById(idMatriculaDiscente);

            if (matriculaDiscente.isEmpty()){
                throw new IllegalArgumentException("Matricula de discente com o ID " + idMatriculaDiscente + " não encontrada");
            }else {
                MatriculaDiscente matDis = matriculaDiscente.get();
                matDis.setTurma(turma.get());
                Turma turmaSalva =  turmaRepository.save(turma.get());
                for (Materia turmaMateria : turmaSalva.getMaterias()) {
                    DiscenteMateria disMatDis = new DiscenteMateria();
                    disMatDis.setMatriculaDiscente(matDis);
                    disMatDis.setMateria(turmaMateria);
                    matDis.getDiscenteMaterias().add(disMatDis);
                }
                matriculaDiscenteService.salvar(matDis);
                return turmaSalva;
            }
        }else {
            throw new IllegalArgumentException("Turma com o ID " + idTurma + " não encontrada");
        }
    }
}
