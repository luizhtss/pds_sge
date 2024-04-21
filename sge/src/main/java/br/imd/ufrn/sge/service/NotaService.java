package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.relatorio.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<DiscenteMateria> listarTodos() {
        return notaRepository.findAll();
    }

    public Optional<DiscenteMateria> encontrarPorId(Long id) {
        return notaRepository.findById(id);
    }

    public List<DiscenteMateria> encontrarPorMatriculaDiscente(Long matricula_discente) {
        return notaRepository.findByDiscenteMatricula(matricula_discente);
    }

    public List<DiscenteMateria> encontrarPorIdMateria(Long id_materia) {
        return notaRepository.findByMateriaId(id_materia);
    }

    @Transactional
    public DiscenteMateria salvar(DiscenteMateria nota) {
        return notaRepository.save(nota);
    }

}
