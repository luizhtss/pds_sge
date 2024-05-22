package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.repository.DadosPessoaisRepository;
import br.imd.ufrn.sge.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    public List<Docente> findByName(String name) {
        return docenteRepository.findByName(name);
    }

    public Optional<Docente> encontrarPorId(Long id) {
        return docenteRepository.findById(id);
    }

    @Transactional
    public Docente salvar(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Transactional
    public void deletar(Long id) {
    docenteRepository.deleteById(id);
}


    public Optional<Docente> buscarDocentePorIdPessoa(Long idPessoa) {
        return docenteRepository.findByDadosPessoaisId(idPessoa);
    }
}