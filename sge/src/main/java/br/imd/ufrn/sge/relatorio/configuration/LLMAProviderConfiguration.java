package br.imd.ufrn.sge.relatorio.configuration;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMAProviderConfiguration {

    @Value("${relatorio.online.api.url}")
    private String apiUrl;

    @Value("${relatorio.online.api.token}")
    private String apiToken;

    @Value("${relatorio.online.temperature:25}")
    private double temperatureDefault;

    @Value("${relatorio.online.max_tokens:15}")
    private int maxTokensDefault;

    @Value("${relatorio.online.system_prompt:default_system_prompt}")
    private String systemPromptDefault;

    @Value("${relatorio.online.prompt_template:prompt_template}")
    private String promptTemplateDefault;

    public double getTemperatureDefault() {
        return temperatureDefault;
    }

    public int getMaxTokensDefault() {
        return maxTokensDefault;
    }

    public String getPromptTemplateDefault() {
        return promptTemplateDefault;
    }

    public String getSystemPromptDefault() {
        return systemPromptDefault;
    }

    @Bean
    public String apiUrl() {
        return apiUrl;
    }

    @Bean
    public String apiToken() {
        return apiToken;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
