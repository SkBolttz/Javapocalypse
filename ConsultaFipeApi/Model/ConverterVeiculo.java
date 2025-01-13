package com.br.consultarFipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ConverterVeiculo(@JsonAlias("codigo") String Ano, 
                               @JsonAlias("nome") String Combustivel) {}

