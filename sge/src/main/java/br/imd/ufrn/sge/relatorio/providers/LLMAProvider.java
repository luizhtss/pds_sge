package br.imd.ufrn.sge.relatorio.providers;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.configuration.LLMAProviderConfiguration;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioPessoal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LLMAProvider implements ILLMProvider {

    @Autowired
    private LLMAProviderConfiguration config;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String apiUrl;
    private final String apiToken;
    private  final FileLoader fileLoader;

    private final String systemPromptRelatorioAcademico;
    private final String systemPromptRelatorioPessoal;



    public LLMAProvider(OkHttpClient client, ObjectMapper objectMapper, String apiUrl, String apiToken, ResourceLoader resourceLoader) throws IOException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
        this.fileLoader = new FileLoader(resourceLoader);
        this.systemPromptRelatorioAcademico = fileLoader.carregarArquivoComoString("llm/system_prompt_academico.txt");
        this.systemPromptRelatorioPessoal = fileLoader.carregarArquivoComoString("llm/system_prompt_pessoal.txt");

    }

    @Override
    @CircuitBreaker(name = "gerarRelatorioBaseAcademico", fallbackMethod = "gerarRelatorioBaseAcademicoOffline")
    public Relatorio gerarRelatorioBaseAcademico(String data, MatriculaDiscente matriculaDiscente) throws InterruptedException, IOException {
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> requestBodyMap = new HashMap<>();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("top_p", 1);
        inputMap.put("prompt", "Gere um relatorio academico com esses dados: " + data);
        inputMap.put("temperature", config.getTemperatureDefault());
        inputMap.put("system_prompt", this.systemPromptRelatorioAcademico.replace("\n", " "));
        inputMap.put("max_new_tokens", config.getMaxTokensDefault());
        inputMap.put("prompt_template", "[INST] <<SYS>>\\n{system_prompt}\\n<</SYS>>\\n\\n{prompt} [/INST]");
        inputMap.put("repetition_penalty", 1.15);
        inputMap.put("min_new_tokens", -1);
        inputMap.put("seed", 22);
        requestBodyMap.put("stream", false);
        requestBodyMap.put("input", inputMap);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestBodyMap);

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(config.apiUrl())
                .post(body)
                .addHeader("Authorization", "Token " + config.apiToken())
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
                relatorioAcademico.setMatriculaDiscente(matriculaDiscente);
                return relatorioAcademico;
            }
        }
    }

    public Relatorio gerarRelatorioBaseAcademicoOffline(String data, Exception e) {
        RelatorioAcademico relatorioAcademico = new RelatorioAcademico();
        relatorioAcademico.setTexto("Relatório offline: " + data);
        relatorioAcademico.setEnchancedByAI(false);
        return relatorioAcademico;
    }

    @Override
    @CircuitBreaker(name = "gerarRelatorioBasePessoal", fallbackMethod = "gerarRelatorioBasePessoalOffline")
    public Relatorio gerarRelatorioBasePessoal(String data) throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> requestBodyMap = new HashMap<>();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("top_p", 1);
        inputMap.put("prompt", "Dados do relatório acadêmico: " + data);
        inputMap.put("temperature", config.getTemperatureDefault());
        inputMap.put("system_prompt", this.systemPromptRelatorioPessoal.replace("\n", " "));
        inputMap.put("max_new_tokens", config.getMaxTokensDefault());
        inputMap.put("prompt_template", "[INST] <<SYS>>\\n{system_prompt}\\n<</SYS>>\\n\\n{prompt} [/INST]");
        inputMap.put("repetition_penalty", 1.15);
        inputMap.put("min_new_tokens", -1);
        inputMap.put("seed", 22);
        requestBodyMap.put("stream", false);
        requestBodyMap.put("input", inputMap);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestBodyMap);

        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(config.apiUrl())
                .post(body)
                .addHeader("Authorization", "Token " + config.apiToken())
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();

        String resultUrl = responseData.split("\"")[3];

        while (true) {
            String status = getStatus(resultUrl);

            if ("processing".equals(status)) {
                System.out.println("Aguardando 5 segundos antes da próxima solicitação...");
                Thread.sleep(5000); // Esperar 5 segundos antes da próxima solicitação
            } else {
                // Imprimir o campo "output" como uma única string
                RelatorioPessoal relatorioPessoal = new RelatorioPessoal();
                String output = getOutput(resultUrl);
                relatorioPessoal.setTexto(output);
                relatorioPessoal.setEnchancedByAI(true);
                System.out.println("Output:");
                System.out.println(output);
                return relatorioPessoal;
            }
        }
    }

    private String getStatus(String url) throws IOException {
        url = "https://api.replicate.com/v1/predictions/" + url;
        System.out.println("Obtendo status da URL: " + url);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Token " + config.apiToken())
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseData);
        String status = jsonNode.get("status").asText();

        return status;

    }

    private String getOutput(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        url = "https://api.replicate.com/v1/predictions/" + url;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Token " + config.apiToken())
                .build();

        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseData);

        // Obtenha o valor do campo "output" como um array de strings
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
