package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
import br.imd.ufrn.sge.exceptions.MatriculaDiscenteNaoEncontradaException;
import br.imd.ufrn.sge.models.discente.ObservacaoDiscente;
import br.imd.ufrn.sge.repository.ObservacaoDiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ObservacaoDiscenteService {

    @Autowired
    private ObservacaoDiscenteRepository observacaoDiscenteRepository;

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    public List<ObservacaoDiscente> listarTodos() {
        return observacaoDiscenteRepository.findAll();
    }

    public List<ObservacaoDiscente> encontrarPorMatriculaDiscente(Long idMatriculaDiscente) {
        return observacaoDiscenteRepository.findByMatriculaDiscenteId(idMatriculaDiscente);
    }
    public Optional<ObservacaoDiscente> encontrarPorId(Long id) {
        return observacaoDiscenteRepository.findById(id);
    }

    @Transactional
    public ObservacaoDiscente salvarObservacao(ObservacaoDiscente observacaoDiscente) throws MatriculaDiscenteNaoEncontradaException{

        if (observacaoDiscente.getMatriculaDiscente() == null) {
            throw new IllegalArgumentException("Matrícula do discente não informada.");
        }

        if (matriculaDiscenteService.encontrarMatriculaPorIdDiscente(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new MatriculaDiscenteNaoEncontradaException("Matrícula do discente não encontrada.");

        observacaoDiscente.setDocenteResponsavel(null);
        return observacaoDiscenteRepository.save(observacaoDiscente);
    }

    public ObservacaoDiscente editarObservacao(ObservacaoDiscente observacaoDiscente) throws Exception{

        if (observacaoDiscente.getMatriculaDiscente() == null) {
            throw new IllegalArgumentException("Matrícula do discente não informada.");
        }

        if (matriculaDiscenteService.encontrarMatriculaPorIdDiscente(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new MatriculaDiscenteNaoEncontradaException("Matrícula do discente não encontrada.");

        if (observacaoDiscenteRepository.findByMatriculaDiscenteId(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new IllegalArgumentException("Observação não encontrada.");

        return observacaoDiscenteRepository.save(observacaoDiscente);
    }

    public void deletarObservacao(Long id) throws IllegalArgumentException{
        if (encontrarPorId(id).isPresent()){
            throw new IdNaoEncontradoException();
        }
        observacaoDiscenteRepository.deleteById(id);
    }
}
