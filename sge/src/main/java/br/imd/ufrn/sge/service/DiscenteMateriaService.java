package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.relatorio.repository.DiscenteMateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscenteMateriaService {

    @Autowired
    private DiscenteMateriaRepository discenteMateriaRepository;

    public List<DiscenteMateria> listarTodos() {
        return discenteMateriaRepository.findAll();
    }

    public Optional<DiscenteMateria> encontrarPorId(Long id) {
        return discenteMateriaRepository.findById(id);
    }

    public List<DiscenteMateria> encontrarPorMatriculaDiscente(Long matricula_discente) {
        return discenteMateriaRepository.findByDiscenteMatricula(matricula_discente);
    }

    public List<DiscenteMateria> encontrarPorIdMateria(Long id_materia) {
        return discenteMateriaRepository.findByMateriaId(id_materia);
    }

    public boolean todasUnidadesPreenchidas(Long idMatriculaDiscente) {
        return discenteMateriaRepository.todasUnidadesPreenchidas(idMatriculaDiscente);
    }

    @Transactional
    public DiscenteMateria salvar(DiscenteMateria nota) {
        return discenteMateriaRepository.save(nota);
    }

}
