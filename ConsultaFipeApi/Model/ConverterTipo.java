package com.br.consultarFipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record ConverterTipo(@JsonAlias("codigo")int Codigo,
                            @JsonAlias("nome")String Nome) {   
}
