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

public abstract class AbstractLLMProvider implements ILLMProvider {

    protected final OkHttpClient client;
    protected final ObjectMapper objectMapper;
    protected final LLMProviderConfiguration.ModelConfig modelConfig;
    protected final FileLoader fileLoader;

    @Autowired
    public AbstractLLMProvider(OkHttpClient client, ObjectMapper objectMapper, LLMProviderConfiguration config, String modelName, ResourceLoader resourceLoader) throws IOException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.modelConfig = config.getModels().get(modelName);
        this.fileLoader = new FileLoader(resourceLoader);
    }

    @Override
    public abstract Relatorio gerarRelatorioBaseAcademico(String data) throws InterruptedException, IOException;

    protected abstract String getStatus(String url) throws IOException;

    protected abstract String getOutput(String url) throws IOException;

}
