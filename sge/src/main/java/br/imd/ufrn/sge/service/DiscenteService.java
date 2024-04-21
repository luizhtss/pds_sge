package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.repository.DiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    public Discente encontrarDiscente(Long idDiscente) {
        return discenteRepository.findById(idDiscente).orElse(null);
    }

    @Transactional
    public Discente salvarDiscente(Discente discente) {
        return discenteRepository.save(discente);
    }

    public void deletarDiscente(Long idDiscente) {
        discenteRepository.deleteById(idDiscente);
    }

    public Discente atualizarDiscente(Discente discente) {
        return discenteRepository.save(discente);
    }

    public Optional<Discente> buscarDiscentePorIdPessoa(Long idPessoa) {
        return discenteRepository.findByIdPessoa(idPessoa);
    }
}
