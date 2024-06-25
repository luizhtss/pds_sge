package br.imd.ufrn.sge.relatorio.providers;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileLoader {
    private final ResourceLoader resourceLoader;

    public FileLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public FileLoader() {
        this.resourceLoader = new ClassPathXmlApplicationContext();
    }

    public String carregarArquivoComoString(String caminhoDoArquivo) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + caminhoDoArquivo);
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(resource.getInputStream(), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        }

        return stringBuilder.toString();
    }
}

