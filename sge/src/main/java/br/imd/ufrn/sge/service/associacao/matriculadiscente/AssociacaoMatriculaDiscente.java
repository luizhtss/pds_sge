package br.imd.ufrn.sge.service.associacao.matriculadiscente;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.service.MatriculaDiscenteService;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Year;
import java.util.Optional;

@Service
public class AssociacaoMatriculaDiscente {

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    @Autowired
    TurmaService turmaService;

    public void associarMatriculaDiscenteEmTurma(Long idMatriculaDiscente, Long idTurma) {
        Optional<MatriculaDiscente> matriculaDiscenteDB = matriculaDiscenteService.encontrarMatriculaPorIdMatriculaEAno(idMatriculaDiscente, Year.now().getValue());
        if (matriculaDiscenteDB.isPresent()){
            if (turmaService.encontrarPorId(idTurma).isPresent()){
                if (!turmaService.isDiscenteMatriculado(matriculaDiscenteDB.get().getDiscente().getId(), idTurma)){
                    turmaService.matricularDiscente(idMatriculaDiscente, idTurma);
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
