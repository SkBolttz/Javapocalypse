package Projeto_Final_Listas;

import java.util.Collections;
import java.util.Scanner;

import Projeto_Final_Listas.Compra.Ordenacao;

public class Principal {
    public static void main(String[] args) {

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras(0);
        
        Ordenacao ord = new Ordenacao();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao supermercado do Henrique!");

        System.out.println("Informe o valor do Cartão de Crédito:");
        carrinho.setLimite(sc.nextDouble());

        sc.nextLine();

        String continuar = "S";

        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("Escolha uma das opções abaixo: \n" +
                    "1 - Adicionar produto \n" +
                    "2 - Remover produto \n" +
                    "3 - Listar produtos \n" +
                    "4 - Calcular total \n" +
                    "5 - Sair");

            int opcao = sc.nextInt();

            sc.nextLine();

            switch (opcao) {
                case 1:
                    Compra produto = new Compra(" ", 0);
                    System.out.println("Informe o produto que deseja adicionar:");
                    produto.setNome(sc.nextLine());
                    System.out.println("Qual o valor do produto?");
                    produto.setValor(sc.nextDouble());
                    sc.nextLine();
                    carrinho.adicionarCompra(produto);
                    System.out.println("Produto adicionado com sucesso!");
                    System.out.println("Saldo atual: " + carrinho.getSaldo() + "\n" +
                    "Limite atual: " + carrinho.getLimite());
                    break;

                case 2:
                    Compra rProduto = new Compra(" ", 0);
                    System.out.println("Qual o nome do produto que deseja remover?");
                    rProduto.setNome(sc.nextLine());
                    carrinho.removerCompra(rProduto);
                    System.out.println("Compra removida com sucesso!");
                    break;

                case 3:
                    System.out.println("Listando compras...");
                    Collections.sort(carrinho.getCompras(), ord);
                    carrinho.getCompras().forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Calculando total...");
                    carrinho.calcularValor();
                    break;

                case 5:
                    System.out.println("Saindo...");
                    continuar = "N";
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        sc.close();
    }
}
