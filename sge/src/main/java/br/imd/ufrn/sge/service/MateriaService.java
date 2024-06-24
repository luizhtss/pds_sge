package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
import br.imd.ufrn.sge.exceptions.RecebendoValoresNullException;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.repository.DocenteRepository;
import br.imd.ufrn.sge.repository.MateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository ;

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Materia> listarTodos() {return materiaRepository.findAll();}

    public Optional<Materia> encontrarPorId(Long id) {
        if (materiaRepository .findById(id).isEmpty()){
            throw new IdNaoEncontradoException();
        }
        return materiaRepository .findById(id);
    }

    public Optional<Materia> encontrarPorDocente(Long id) {
        if (materiaRepository .findByDocenteId(id).isEmpty()){
            throw new IdNaoEncontradoException();
        }
        return materiaRepository .findByDocenteId(id);
    }

    public Optional<Materia> encontrarPorTurma(Long id) {
        if (materiaRepository .findByTurmaId(id).isEmpty()){
            throw new IdNaoEncontradoException();
        }
        return materiaRepository .findByTurmaId(id);
    }


    @Transactional
    public Materia salvar(Materia materia) {
        if(materia.getNome().isEmpty()){throw new RecebendoValoresNullException("Nome de materia não pode ser vazio");}
        return materiaRepository.save(materia);
    }
//salvando materia com o id do docente
    @Transactional
    public Materia salvar(Long id,Materia materia) {
        if (!docenteRepository.existsById(id)){throw new IdNaoEncontradoException();}
        materia.setDocente(docenteRepository.findById(id).get());
        return materiaRepository.save(materia);
    }

    @Transactional
    public void deletar(Long id) {
        if (materiaRepository.existsById(id)) {
            materiaRepository.deleteById(id);
        }else {throw new IdNaoEncontradoException();}

    }
}
