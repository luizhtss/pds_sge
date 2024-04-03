package br.imd.ufrn.sge.relatorio.providers;

import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import br.imd.ufrn.sge.relatorio.interfaces.IRelatorioProvider;
import br.imd.ufrn.sge.relatorio.configuration.LLMAProviderConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LLMAProvider implements IRelatorioProvider {

    @Autowired
    private LLMAProviderConfiguration config;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String apiUrl;
    private final String apiToken;

    public LLMAProvider(OkHttpClient client, ObjectMapper objectMapper, String apiUrl, String apiToken) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
    }

    @Override
    @CircuitBreaker(name = "gerarRelatorioBaseAcademico", fallbackMethod = "gerarRelatorioBaseAcademicoOffline")
    public Relatorio gerarRelatorioBaseAcademico(String data) {
        Map<String, Object> requestBodyMap = new HashMap<>();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("top_p", 1);
        inputMap.put("prompt", data + config.getPromptTemplateDefault());
        inputMap.put("temperature", config.getTemperatureDefault());
        inputMap.put("system_prompt", config.getSystemPromptDefault());
        inputMap.put("max_new_tokens", config.getMaxTokensDefault());
        requestBodyMap.put("stream", false);
        requestBodyMap.put("input", inputMap);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = null;
        try {
            body = RequestBody.create(objectMapper.writeValueAsString(requestBodyMap), mediaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Token " + apiToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response);
            }

            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String textoBase = jsonNode.get("urls").get("stream").asText();

            RelatorioAcademico relatorioAcademico = new RelatorioAcademico();
            relatorioAcademico.setTexto(textoBase);
            relatorioAcademico.setEnchancedByAI(true);

            return relatorioAcademico;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public Relatorio gerarRelatorioBasePessoal(String data) {
        Map<String, Object> requestBodyMap = new HashMap<>();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("top_p", 1);
        inputMap.put("prompt", data + config.getPromptTemplateDefault());
        inputMap.put("temperature", config.getTemperatureDefault());
        inputMap.put("system_prompt", config.getSystemPromptDefault());
        inputMap.put("max_new_tokens", config.getMaxTokensDefault());
        requestBodyMap.put("stream", false);
        requestBodyMap.put("input", inputMap);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = null;
        try {
            body = RequestBody.create(objectMapper.writeValueAsString(requestBodyMap), mediaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Token " + apiToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response);
            }

            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String textoBase = jsonNode.get("urls").get("stream").asText();

            RelatorioAcademico relatorioAcademico = new RelatorioAcademico();
            relatorioAcademico.setTexto(textoBase);
            relatorioAcademico.setEnchancedByAI(true);

            return relatorioAcademico;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Relatorio gerarRelatorioBasePessoalOffline(String data, Exception e) {
        RelatorioAcademico relatorioAcademico = new RelatorioAcademico();
        relatorioAcademico.setTexto("Relatório offline: " + data);
        relatorioAcademico.setEnchancedByAI(false);
        return relatorioAcademico;
    }
}
