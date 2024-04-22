package br.imd.ufrn.sge.service.associacao;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.MateriaService;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociacaoMateriaService {

    @Autowired
    MateriaService materiaService;

    @Autowired
    TurmaService turmaService;

    public Turma associarMateriaTurma(Long idMateria, Long idTurma) throws IllegalArgumentException{
        Optional<Materia> materiaDB = materiaService.encontrarPorId(idMateria);
        if (materiaDB.isPresent()) {
            Materia materia = materiaDB.get();
            Optional<Turma> turmaDB = turmaService.encontrarPorId(idTurma);
            if (turmaDB.isPresent()) {
                Turma turma = turmaDB.get();
                turma.getMaterias().add(materia);
                return turmaService.salvar(turma);
            } else {
                throw new IllegalArgumentException("Turma com o ID " + idTurma + " não encontrada");
            }
        } else {
            throw new IllegalArgumentException("Materia com o ID " + idMateria + " não encontrada");
        }
    }


}
