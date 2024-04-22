package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.repository.MatriculaDiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MatriculaDiscenteService {

    @Autowired
    MatriculaDiscenteRepository matriculaDiscenteRepository;

    public Optional<MatriculaDiscente> encontrarMatriculaPorIdPessoaEAno(Long idPessoa, int ano) {
        return matriculaDiscenteRepository.findByIdPessoaAndAno(idPessoa, ano);
    }

    public Optional<MatriculaDiscente> encontrarMatriculaPorIdDiscente(Long idDiscente) {
        return matriculaDiscenteRepository.findByIdDiscente(idDiscente);
    }

    public Optional<MatriculaDiscente> findById(Long id) {
        return matriculaDiscenteRepository.findById(id);
    }

    public Optional<MatriculaDiscente> encontrarMatriculaPorIdMatriculaEAno(Long idMatricula, int ano) {
        return matriculaDiscenteRepository.findByIdMatriculaAndAno(idMatricula, ano);
    }

    public MatriculaDiscente salvarMatricula(MatriculaDiscente matriculaDiscente) {
        return matriculaDiscenteRepository.save(matriculaDiscente);
    }

    public Optional<MatriculaDiscente> obterMatriculaDiscente(Long idMatriculaDiscente) {
        return matriculaDiscenteRepository.findById(idMatriculaDiscente);
    }

    public void salvar(MatriculaDiscente matDis) {
        matriculaDiscenteRepository.save(matDis);
    }
}
