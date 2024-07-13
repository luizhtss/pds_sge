package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.Frequencia;
import br.imd.ufrn.sge.repository.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    public List<Frequencia> listarTodos() {
        return frequenciaRepository.findAll();
    }

    public Optional<Frequencia> encontrarPorId(Long id) {
        return frequenciaRepository.findById(id);
    }

    public Frequencia salvar(Frequencia frequencia) {
        List<Frequencia> existingFrequencias = frequenciaRepository.findByDiscenteMateria(frequencia.getDiscenteMateria());
        for (Frequencia existingFrequencia : existingFrequencias) {
            if (existingFrequencia.equals(frequencia)) {
                return existingFrequencia; // Return the existing one if it's a duplicate
            }
        }
        return frequenciaRepository.save(frequencia); // Save if unique
    }

    public void deletar(Long id) {
        frequenciaRepository.deleteById(id);
    }
}