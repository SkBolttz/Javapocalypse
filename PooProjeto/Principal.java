package WProjetoPoo;

import java.util.Scanner;
import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String continuar = "S";
        GestorReceitas gestorReceita = new GestorReceitas();
        GestorDespesas gestorDespesas = new GestorDespesas();
        GestorFinancas gestorFinancas = new GestorFinancas();

        gestorFinancas.setReceitas(gestorReceita.getReceitas());
        gestorFinancas.setDespesas(gestorDespesas.getDespesas());
       
        
        while (continuar.equalsIgnoreCase("S")) {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Incluir Receita:");
            System.out.println("2 - Incluir Despesa:");
            System.out.println("3 - Listar Receitas:");
            System.out.println("4 - Listar Despesas:");
            System.out.println("5 - Consultar Extrato:");
            System.out.println("6 - Consultar Todo Saldo:");
            System.out.println("7 - Consultar Saldo Atual:");
            System.out.println("8 - Sair");
            System.out.print("Digite o número da opção: ");

            int opcao = sc.nextInt();

            sc.nextLine();
            switch (opcao) {
                case 1:
                    GestorReceitas receita = new GestorReceitas();

                    System.out.println("Informe o nome da receita:");
                    receita.setNome(sc.nextLine());
                    System.out.println("Informe o valor da receita:");
                    receita.setValor(sc.nextDouble());

                    sc.nextLine();

                    System.out.println("Informe a descrição da receita:");
                    receita.setDescricao(sc.nextLine());
                    System.out.println("Informe a categotia da receita:");
                    System.out.println("Categorias: \n"
                            + "1 - Férias\n"
                            + "2 - Décimo Terceiro\n"
                            + "3 - Salário\n"
                            + "4 - Outros\n"
                            + "5 - Pensão\n");

                    int categoriaR = sc.nextInt();

                    if (categoriaR == 1) {
                        receita.setCategoriaReceita(CategoriaReceitas.FERIAS);
                    } else if (categoriaR == 2) {
                        receita.setCategoriaReceita(CategoriaReceitas.DECIMO_TERCEIRO);
                    } else if (categoriaR == 3) {
                        receita.setCategoriaReceita(CategoriaReceitas.RECEBIMENTO_SALARIO);
                    } else if (categoriaR == 4) {
                        receita.setCategoriaReceita(CategoriaReceitas.OUTROS);
                    } else if (categoriaR == 5) {
                        receita.setCategoriaReceita(CategoriaReceitas.PENSAO);
                    } else {
                        System.out.println("Opção inválida!");
                        return;
                    }

                    sc.nextLine();

                    System.out.println("A receita será incluída na data atual? (S/N)");
                    String dataReceita = sc.nextLine();

                    if (dataReceita.equalsIgnoreCase("S")) {
                        receita.setDataReceita(LocalDate.now());
                    } else {
                        System.out.println("Informe a data da despesa (AAAA-MM-DD):");
                        receita.setDataReceita(LocalDate.parse(sc.nextLine()));
                    }

                    gestorReceita.incluirReceitas(receita);
                    System.out.println("Receita incluída com sucesso!");
                    break;

                case 2:

                    GestorDespesas despesa = new GestorDespesas();

                    System.out.println("Informe o nome da despesa:");
                    despesa.setNome(sc.nextLine());
                    System.out.println("Informe o valor da despesa:");
                    despesa.setValor(sc.nextDouble());

                    sc.nextLine();

                    System.out.println("Informe a descrição da despesa:");
                    despesa.setDescricao(sc.nextLine());

                    System.out.println("Informe a categotia da receita:");

                    System.out.println("Categorias: \n"
                            + "1 - Alimentação\n"
                            + "2 - Transporte\n"
                            + "3 - Saúde\n"
                            + "4 - Educação\n"
                            + "5 - Entreterimento\n"
                            + "6 - Outros");

                    int categoriaD = sc.nextInt();

                    if (categoriaD == 1) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.ALIMENTACAO);
                    } else if (categoriaD == 2) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.TRANSPORTES);
                    } else if (categoriaD == 3) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.SAUDE);
                    } else if (categoriaD == 4) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.EDUCACAO);
                    } else if (categoriaD == 5) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.ENTRETERIMENTO);
                    } else if (categoriaD == 6) {
                        despesa.setCategoriaDespesas(CategoriaDespesas.OUTROS);
                    } else {
                        System.out.println("Opção inválida!");
                    }

                    sc.nextLine();

                    System.out.println("A despesa será incluída na data atual? (S/N)");
                    String dataDespesa = sc.nextLine();

                    if (dataDespesa.equalsIgnoreCase("S")) {
                        despesa.setLocalDateDespesas(LocalDate.now());
                    } else {
                        System.out.println("Informe a data da despesa (AAAA-MM-DD):");
                        despesa.setLocalDateDespesas(LocalDate.parse(sc.nextLine()));
                    }

                    gestorDespesas.incluirDespesas(despesa);
                    System.out.println("Despesa incluida com sucesso!");
                    break;

                case 3:

                    System.out.println("Listando receitas...");
                    gestorReceita.listarReceitas();
                    break;

                case 4:

                    System.out.println("Listando despesas...");
                    gestorDespesas.listarDespesas();
                    break;

                case 5:

                    System.out.println("Consultando Extrato...");
                    System.out.println(gestorFinancas.consultarExtratoDetalhado());

                    break;
                case 6:

                    System.out.println("Consultando saldo...");
                    System.out.println(gestorFinancas.consultarTodoSaldo());
                    break;

                case 7:

                    System.out.println("Consultando saldo...");
                    System.out.println(gestorFinancas.calcularSaldoAtePeriodoAtual());
                    break;
                case 8:

                    System.out.println("saindo do promgrada, obrigado por utilizar o programa!");
                    continuar = "N";
                    break;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        }

        sc.close();
    }
}
