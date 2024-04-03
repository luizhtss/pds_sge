package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.relatorio.repository.DadosPessoaisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisRepository dadosPessoaisRepository;

    public List<DadosPessoais> listarTodos() {
        return dadosPessoaisRepository.findAll();
    }

    public Optional<DadosPessoais> encontrarPorId(Long id) {
        return dadosPessoaisRepository.findById(id);
    }

    @Transactional
    public DadosPessoais salvar(DadosPessoais pessoa) {
        return dadosPessoaisRepository.save(pessoa);
    }

    @Transactional
    public void deletar(Long id) {
        dadosPessoaisRepository.deleteById(id);
    }

    public List<DadosPessoais> buscarDadosPeloAnoDeCriacao(int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);

        Date data = calendar.getTime();

        return dadosPessoaisRepository.findByYearOfCreation(data);
    }
}
