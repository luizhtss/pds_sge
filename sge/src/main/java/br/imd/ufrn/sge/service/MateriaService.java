package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
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


    @Transactional
    public Materia salvar(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Transactional
    public Materia salvar(Long id,Materia materia) {
        materia.setDocente(docenteRepository.findById(id).get());
        return materiaRepository.save(materia);
    }

    @Transactional
    public void deletar(Long id) { materiaRepository.deleteById(id);}
}
