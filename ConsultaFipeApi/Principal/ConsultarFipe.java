package com.br.consultarFipe.Principal;

import com.br.consultarFipe.Model.ConverterTipo;
import com.br.consultarFipe.Model.ConverterVeiculo;
import com.br.consultarFipe.Model.ConverterAno;
import com.br.consultarFipe.Model.ConverterModelo;
import com.br.consultarFipe.Services.ConsultarApi;
import com.br.consultarFipe.Services.ConverteDados;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsultarFipe {

        private ConsultarApi consultarApi = new ConsultarApi();
        private ConverteDados converterDados = new ConverteDados();

        public void verificarFipe() {

                Scanner sc = new Scanner(System.in);
                var continuar = "S";

                while (!continuar.equalsIgnoreCase("N")) {
                        System.out.println("Informe o tipo de veículo conforme tabela abaixo:");
                        System.out.println("1 - Carro \n" +
                                        "2 - Moto \n" +
                                        "3 - Caminhão");

                        var tipoVeiculo = sc.nextLine().toLowerCase();

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        var consultarModelo = consultarApi
                                        .obterDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo
                                                        + "/marcas/");

                        List<ConverterTipo> converterTipos = converterDados.obterListaDados(consultarModelo,
                                        com.br.consultarFipe.Model.ConverterTipo.class);

                        System.out.println("Veículos do tipo: " + tipoVeiculo.toUpperCase());

                        converterTipos.stream()
                                        .forEach(tipo -> System.out.println("Código - " + tipo.Codigo() + "    |    "
                                                        + "Nome - " + tipo.Nome()));

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        System.out.println("Informe o modelo pelo código a esquerda da Tabela:");

                        var codigoEscolhido = sc.nextLine();

                        var consultarVeiculo = consultarApi
                                        .obterDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo
                                                        + "/marcas/" + codigoEscolhido + "/modelos/");

                        ConverterModelo converterModelo = converterDados.obterDados(consultarVeiculo,
                                        com.br.consultarFipe.Model.ConverterModelo.class);

                        List<ConverterTipo> modelos = converterModelo.modelos();

                        System.out.println("Veículos do tipo: " + tipoVeiculo.toUpperCase());

                        modelos.stream()
                                        .forEach(e -> System.out.println(
                                                        "Código - " + e.Codigo() + "    |    " + "Nome - " + e.Nome()));

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        
                        System.out.println("Informe o nomo/trecho do veículo que deseja procurar: ");
                        var nomeVeiculo = sc.nextLine();

                        List<ConverterTipo> veiculo = modelos.stream()
                                        .map(e -> new ConverterTipo(e.Codigo(), e.Nome()))
                                        .filter(e -> e.Nome().toLowerCase().contains(nomeVeiculo.trim().toLowerCase())) // Comparação
                                                                                                                        // parcial
                                        .collect(Collectors.toList());

                        if (veiculo.isEmpty()) { 
                                System.out.println("Nenhum veículo encontrado que contenha: " + nomeVeiculo);
                        } else {
                                veiculo.forEach(e -> System.out
                                                .println("Código - " + e.Codigo() + "    |    Nome - " + e.Nome()));
                        }

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        System.out.println("Informe o código do veículo que deseja consultar:");
                        var codigoVeiculo = sc.nextLine();

                        var consultarAno = consultarApi.obterDados("https://parallelum.com.br/fipe/api/v1/"
                                        + tipoVeiculo
                                        + "/marcas/" + codigoEscolhido + "/modelos/" + codigoVeiculo + "/anos/");

                        List<ConverterVeiculo> converterAnos = converterDados.obterLista(consultarAno,
                                        com.br.consultarFipe.Model.ConverterVeiculo.class);

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        System.out.println("Veículos disponíveis:");

                        for (int i = 0; i < converterAnos.size(); i++) {
                                                var veiculoD = consultarApi
                                                                .obterDados("https://parallelum.com.br/fipe/api/v1/"
                                                                                + tipoVeiculo
                                                                                + "/marcas/" + codigoEscolhido
                                                                                + "/modelos/" + codigoVeiculo
                                                                                + "/anos/" + converterAnos.get(i).Ano() + "/");

                                                ConverterAno converterAno = converterDados.obterDados(veiculoD,
                                                                com.br.consultarFipe.Model.ConverterAno.class);

                                                System.out.println("Modelo: " + converterAno.Modelo() + " Marca: "
                                                                + converterAno.Marca()
                                                                + " Ano: " + converterAno.Ano() + " Valor: "
                                                                + converterAno.Valor()
                                                                + " Combustível: " + converterAno.Combustivel());
                                        }

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        System.out.println("Deseja realizar uma nova pesquisa? S/N");
                        continuar = sc.nextLine();
                }
                sc.close();
        }
}
