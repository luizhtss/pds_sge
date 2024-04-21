package br.imd.ufrn.sge.service.associacao;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.service.MateriaService;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociacaoMateria {

    @Autowired
    MateriaService materiaService;

    @Autowired
    TurmaService turmaService;

    public void associarMateriaTurma(Long idMateria, Long idTurma) throws IllegalArgumentException{
        Optional<Materia> materiaDB = materiaService.encontrarPorId(idMateria);
        if (materiaDB.isPresent()){
            Materia materia = materiaDB.get();
            turmaService.encontrarPorId(idTurma).ifPresentOrElse(turma -> {
                //turma.addMateria(materia);
                turmaService.salvar(turma);
            }, () -> {
                throw new IllegalArgumentException("Turma com o ID " + idTurma + " não encontrada");
            });
        }else{
            throw new IllegalArgumentException("Materia com o ID " + idMateria + " não encontrada");
        }
    }


}
