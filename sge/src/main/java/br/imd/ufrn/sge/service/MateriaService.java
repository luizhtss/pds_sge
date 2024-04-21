package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.relatorio.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository ;

    public List<Materia> listarTodos() {return materiaRepository.findAll();}

    public Optional<Materia> encontrarPorId(Long id) {
        return materiaRepository.findById(id);
    }

    @Transactional
    public Materia salvar(Materia materia) {
        return materiaRepository.save(materia);
    }
}
