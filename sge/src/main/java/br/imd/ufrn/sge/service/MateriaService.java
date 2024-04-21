package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.relatorio.repository.MateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void deletar(Long id) {
        materiaRepository.deleteById(id);
    }

}
