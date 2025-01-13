package com.br.consultarFipe.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarApi {
        public String obterDados(String endereco) {
            // Cria um client
            HttpClient client = HttpClient.newHttpClient();
            // Cria uma request
            HttpRequest request = HttpRequest.newBuilder()
            // Endereço da API
                    .uri(URI.create(endereco))
                    // Método GET
                    .GET()
                    .build();
                    // Envia a request
            HttpResponse<String> response = null;
            try {
                // Aguarda a resposta
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Retorna o corpo da resposta
            String json = response.body();
            return json;
        }
    }

