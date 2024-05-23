package br.imd.ufrn.sge.service.associacao;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.MatriculaDiscenteService;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service
public class AssociacaoMatriculaDiscenteService {

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    @Autowired
    TurmaService turmaService;

    public Turma associarMatriculaDiscenteEmTurma(Long idMatriculaDiscente, Long idTurma) throws IllegalArgumentException{
        Optional<MatriculaDiscente> matriculaDiscenteDB = matriculaDiscenteService.encontrarMatriculaPorIdMatriculaEAno(idMatriculaDiscente, Year.now().getValue());
        if (matriculaDiscenteDB.isPresent()){
            if (turmaService.encontrarPorId(idTurma).isPresent()){
                if (!turmaService.isDiscenteMatriculado(matriculaDiscenteDB.get().getDiscente().getId(), idTurma)){
                    return turmaService.matricularDiscente(idMatriculaDiscente, idTurma);
                }else{
                    throw new IllegalArgumentException("Discente já matriculado na turma com o ID " + idTurma);
                }
            }else{
                throw new IllegalArgumentException("Turma com o ID " + idTurma + " não encontrada");
            }
        }else{
            throw new IllegalArgumentException("Matricula de discente com o ID " + idMatriculaDiscente + " não encontrada");
        }
    }

}
