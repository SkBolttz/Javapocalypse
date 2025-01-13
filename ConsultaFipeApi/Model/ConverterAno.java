package com.br.consultarFipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConverterAno(@JsonAlias("Modelo")String Modelo,
                           @JsonAlias("Marca")String Marca,
                           @JsonAlias("AnoModelo")String Ano,
                           @JsonAlias("Valor") String Valor,
                           @JsonAlias("Combustivel")String Combustivel) {   
}
