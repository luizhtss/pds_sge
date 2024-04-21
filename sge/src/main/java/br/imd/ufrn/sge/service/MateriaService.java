package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository ;

    public List<Materia> findByName(String nome_materia) {
        return materiaRepository.findByName(nome_materia);
    }
    public List<Materia> findById(String id_materia) {
        return materiaRepository.findByName(id_materia);
    }
}
