package com.br.consultarFipe.Model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConverterModelo(@JsonAlias("modelos") List<ConverterTipo> modelos) {
    
}
