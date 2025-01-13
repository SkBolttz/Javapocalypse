package com.br.consultarFipe.Services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados implements IConverteDados {
    // Instancia do ObjectMapper
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    // Retorna um objeto do tipo genérico (Tipos genericos aceitam divesos tipos de
    // dados)
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Transforma o json em um objeto
            return objectMapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterListaDados(String json, Class<T> classe) {
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, classe);
            return objectMapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterLista(String json, Class<T> classe) {
        try {
            // Transforma o JSON em uma lista do tipo genérico
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, classe));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desserializar JSON para lista: " + e.getMessage(), e);
        }
    }
}
