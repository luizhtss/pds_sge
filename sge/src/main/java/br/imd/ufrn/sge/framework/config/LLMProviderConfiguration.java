package br.imd.ufrn.sge.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "llm")
public class LLMProviderConfiguration {

    private Map<String, ModelConfig> models = new HashMap<>();

    public Map<String, ModelConfig> getModels() {
        return models;
    }

    public void setModels(Map<String, ModelConfig> models) {
        this.models = models;
    }

    public static class ModelConfig {
        private String apiUrl;
        private String apiToken;
        private String basePrompt;

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }

        public String getBasePrompt() {
            return basePrompt;
        }

        public void setBasePrompt(String basePrompt) {
            this.basePrompt = basePrompt;
        }
    }
}
