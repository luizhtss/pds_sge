package br.imd.ufrn.sge.relatorio.providers;

package br.imd.ufrn.sge.relatorio.providers;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.configuration.LLMProviderConfiguration;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public abstract class AbstractLLMModel implements ILLMProvider {

    protected final OkHttpClient client;
    protected final ObjectMapper objectMapper;
    protected final LLMProviderConfiguration.ModelConfig modelConfig;
    protected final FileLoader fileLoader;

    @Autowired
    public AbstractLLMProvider(String modelName) throws IOException {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.modelConfig = config.getModels().get(modelName);
        this.fileLoader = new FileLoader(resourceLoader);
    }

    @Override
    public abstract Relatorio gerarRelatorio(String data) throws InterruptedException, IOException;

    public  void carregarPrompt(){
        this.systemPromptRelatorioAcademico = fileLoader.carregarArquivoComoString(modelConfig.getBasePrompt());
        this.systemPromptRelatorioPessoal = fileLoader.carregarArquivoComoString(modelConfig.getBasePrompt());
    }

}
