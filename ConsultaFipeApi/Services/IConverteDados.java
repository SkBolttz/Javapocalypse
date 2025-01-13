package com.br.consultarFipe.Services;
public interface IConverteDados {

    // Retorna um objeto do tipo genérico
    <T> T obterDados(String json, Class<T> clazz);
}
