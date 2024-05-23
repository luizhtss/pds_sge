package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.repository.DiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

//    public Discente encontrarDiscente(Long idDiscente) {
//        return discenteRepository.findById(idDiscente).orElse(null);
//    }

//    public Optional<Discente>  encontrarDiscente(Long idDiscente) {
//    return discenteRepository.findById(idDiscente);
//}
    public Optional<Discente> encontrarDiscente(Long id) {
        if (discenteRepository.findById(id).isEmpty()){
            throw new IdNaoEncontradoException();
        }
        return discenteRepository.findById(id);
    }
    public boolean dadosPessoaisJaExistem(Discente docente) {
        List<Discente> dadosPessoaisJaExistem = discenteRepository.findByDadosPessoais(docente.getDadosPessoais().getId());
        return !dadosPessoaisJaExistem.isEmpty();
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
