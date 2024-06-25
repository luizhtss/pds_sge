package br.imd.ufrn.sge.relatorio.providers;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.configuration.LLMProviderConfiguration;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LLAMA2 extends AbstractLLMProvider {

    private final String systemPromptRelatorioAcademico;
    private final String systemPromptRelatorioPessoal;

    @Autowired
    public LLMAProvider(OkHttpClient client, ObjectMapper objectMapper, LLMProviderConfiguration config, ResourceLoader resourceLoader) throws IOException {
        super(client, objectMapper, config, "llama2", resourceLoader);
        this.systemPromptRelatorioAcademico = fileLoader.carregarArquivoComoString(modelConfig.getBasePrompt());
        this.systemPromptRelatorioPessoal = fileLoader.carregarArquivoComoString(modelConfig.getBasePrompt());
    }

    @Override
    @CircuitBreaker(name = "gerarRelatorioBaseAcademico", fallbackMethod = "gerarRelatorioBaseAcademicoOffline")
    public Relatorio gerarRelatorio(String data) throws InterruptedException, IOException {
        Map<String, Object> requestBodyMap = new HashMap<>();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("top_p", 1);
        inputMap.put("prompt", "Dados do relatório acadêmico: " + data);
        inputMap.put("temperature", config.getTemperatureDefault());
        inputMap.put("system_prompt", this.systemPromptRelatorioAcademico.replace("\n", " "));
        inputMap.put("max_new_tokens", config.getMaxTokensDefault());
        inputMap.put("prompt_template", "[INST] <<SYS>>\\n{system_prompt}\\n<</SYS>>\\n\\n{prompt} [/INST]");
        inputMap.put("repetition_penalty", 1.15);
        inputMap.put("min_new_tokens", -1);
        inputMap.put("seed", 22);
        requestBodyMap.put("stream", false);
        requestBodyMap.put("input", inputMap);

        String jsonBody = objectMapper.writeValueAsString(requestBodyMap);

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(modelConfig.getApiUrl())
                .post(body)
                .addHeader("Authorization", "Token " + modelConfig.getApiToken())
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();

        String resultUrl = responseData.split("\"")[3];

        while (true) {
            String status = getStatus(resultUrl);

            if ("processing".equals(status)) {
                System.out.println("Aguardando 5 segundos antes da próxima solicitação...");
                Thread.sleep(5000);
            } else {
                RelatorioAcademico relatorioAcademico = new RelatorioAcademico();
                String output = getOutput(resultUrl);
                relatorioAcademico.setTexto(output);
                relatorioAcademico.setEnchancedByAI(true);
                return relatorioAcademico;
            }
        }
    }

    protected String getStatus(String url) throws IOException {
        url = "https://api.replicate.com/v1/predictions/" + url;
        System.out.println("Obtendo status da URL: " + url);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Token " + modelConfig.getApiToken())
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        JsonNode jsonNode = objectMapper.readTree(responseData);
        return jsonNode.get("status").asText();
    }

    protected String getOutput(String url) throws IOException {
        url = "https://api.replicate.com/v1/predictions/" + url;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Token " + modelConfig.getApiToken())
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        JsonNode jsonNode = objectMapper.readTree(responseData);

        JsonNode outputNode = jsonNode.get("output");
        StringBuilder outputStringBuilder = new StringBuilder();
        if (outputNode != null && outputNode.isArray()) {
            for (JsonNode node : outputNode) {
                outputStringBuilder.append(node.asText());
            }
            return outputStringBuilder.toString();
        }
        return "";
    }
}
