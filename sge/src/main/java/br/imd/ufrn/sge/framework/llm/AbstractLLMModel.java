package br.imd.ufrn.sge.framework.llm;

import br.imd.ufrn.sge.framework.config.LLMProviderConfiguration;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import br.imd.ufrn.sge.relatorio.providers.FileLoader;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

import java.io.IOException;

public abstract class AbstractLLMModel implements ILLMProvider {

    protected final OkHttpClient client;
    protected final ObjectMapper objectMapper;
    protected final LLMProviderConfiguration.ModelConfig modelConfig;
    protected final FileLoader fileLoader;
    protected String llmName;
    protected String promptBase;

    protected AbstractLLMModel(OkHttpClient client, ObjectMapper objectMapper, LLMProviderConfiguration.ModelConfig modelConfig, FileLoader fileLoader, String llmName) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.modelConfig = modelConfig;
        this.fileLoader = fileLoader;
        this.llmName = llmName;
    }

    // Template method.
    @Override
    public Relatorio gerarRelatorioBaseAcademico(String data, MatriculaDiscente matriculaDiscente) throws InterruptedException, IOException {
        carregarPromptBase();
        return processarRelatorio(data, matriculaDiscente);
    }

    protected void carregarPromptBase() throws IOException {
        String prompt = fileLoader.carregarArquivoComoString(modelConfig.getBasePrompt());
        promptBase = prompt;
    }

    protected abstract Relatorio processarRelatorio(String data, MatriculaDiscente matriculaDiscente) throws InterruptedException, IOException;

}
