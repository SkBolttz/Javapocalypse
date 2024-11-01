package ProjetoBiblioteca;

import java.util.Scanner;

public class SistemaBiblioteca {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GerenciamentoDeClientes gerenciamentoClientes = new GerenciamentoDeClientes();
        CatalogoLivros catalogoLivros = new CatalogoLivros();
        GerenciamentoDeEmprestimos gerenciamentoEmprestimos = new GerenciamentoDeEmprestimos();

        int contador_id = 0;
        int opcao = 0;
        boolean encontrado = false;

        System.out.println("Seja bem-vindo(a) ao sistema de biblioteca");
        System.out.println("O que você deseja fazer?");

        while (opcao != 16) {
            System.out.println("1 - Cadastrar cliente: \n" +
                    "2 - Buscar cliente por ID: \n" +
                    "3 - Buscar cliente por nome: \n" +
                    "4 - Atualizar cliente: \n" +
                    "5 - Exibir lista de clientes: \n" +
                    "6 - Adicionar livro: \n" +
                    "7 - Remover livro: \n" +
                    "8 - Buscar livro por título: \n" +
                    "9 - Buscar livro por autor: \n" +
                    "10 - Buscar livro por IBSN: \n" +
                    "11 - Exibir lista de livros: \n" +
                    "12 - Adicionar emprestimo: \n" +
                    "13 - Registrar devolucao: \n" +
                    "14 - Verificar disponibilidade: \n" +
                    "15 - Listar emprestimos: \n" +
                    "16 - Sair: \n");

            opcao = sc.nextInt();

            sc.nextLine();

            switch (opcao) {
                case 1:
                    Cliente novoCliente = new Cliente();

                    contador_id += 1;
                    System.out.println("Informe os dados do cliente:");

                    System.out.println("Nome: ");
                    novoCliente.setNome(sc.nextLine()); // Captura nome completo

                    System.out.println("Endereço: ");
                    novoCliente.setEndereco(sc.nextLine()); // Captura endereço completo

                    System.out.println("Telefone: ");
                    novoCliente.setTelefone(sc.nextInt()); // Captura o telefone como um número inteiro

                    sc.nextLine(); // Limpa o buffer de entrada após capturar o número inteiro

                    novoCliente.setId(contador_id);
                    gerenciamentoClientes.adicionarCliente(novoCliente); // Adiciona o cliente à lista
                    System.out.println("Cliente: " + novoCliente.getNome() + ", cadastrado com sucesso!");

                    break;

                case 2:
                    System.out.println("Informe o id do cliente que deseja buscar: ");
                    int id = sc.nextInt();
                    gerenciamentoClientes.buscarClientePorID(id);

                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Informe o nome do cliente que deseja buscar: ");
                    String nome = sc.nextLine();
                    if (!gerenciamentoClientes.buscarClientePorNome(nome)) {

                    }
                    break;

                case 4:
                    System.out.println("Informe o id do cliente que deseja atualizar:");
                    int idAtualizar = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Informe os novos dados do cliente:");

                    System.out.println("Nome: ");
                    String nomeNovo = sc.nextLine();

                    System.out.println("Endereço: ");
                    String enderecoNovo = sc.nextLine();

                    System.out.println("Telefone: ");
                    int telefoneNovo = sc.nextInt();

                    sc.nextLine();

                    gerenciamentoClientes.atualizarCliente(idAtualizar, nomeNovo, enderecoNovo, telefoneNovo);
                    break;

                case 5:
                    System.out.println("Exibindo lista de clientes...");
                    gerenciamentoClientes.listarClientes();
                    break;

                case 6:
                    Livro livro = new Livro();

                    System.out.println("Informe o nome do livro que deseja adicionar: ");
                    livro.setTitulo(sc.nextLine());

                    System.out.println("Autor: ");
                    livro.setAutor(sc.nextLine());

                    System.out.println("ISBN: ");
                    livro.setIsnb(sc.nextLine());

                    catalogoLivros.adicionarLivro(livro);
                    System.out.println("O livro " + livro.getTitulo() + " foi adicionado com sucesso!");
                    break;

                case 7:

                    encontrado = false;
                    System.out.println("Informe o nome do livro que deseja remover: ");
                    String nomeRemover = sc.nextLine();
                    for (int i = 0; i < catalogoLivros.getListarLivros().size(); i++) {
                        if (catalogoLivros.getListarLivros().get(i).getTitulo().equalsIgnoreCase(nomeRemover)) {
                            catalogoLivros.removerLivro(catalogoLivros.getListarLivros().get(i));
                            System.out.println("Livro " + nomeRemover + " removido com sucesso!");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Livro nao encontrado!");
                    }
                    break;

                case 8:

                    encontrado = false;
                    System.out.println("Informe o livro que deseja buscar: ");
                    String nomeLivro = sc.nextLine();
                    for (int i = 0; i < catalogoLivros.getListarLivros().size(); i++) {
                        if (catalogoLivros.getListarLivros().get(i).getTitulo().equalsIgnoreCase(nomeLivro)) {
                            System.out.println("Livro " + nomeLivro + " Encontrado!");
                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Livro nao encontrado!");
                    }
                    break;

                case 9:

                    encontrado = false;
                    System.out.println("Informe o autor do Livro que deseja buscar: ");
                    String autorLivro = sc.nextLine();
                    for (int i = 0; i < catalogoLivros.getListarLivros().size(); i++) {
                        if (catalogoLivros.getListarLivros().get(i).getAutor().equalsIgnoreCase(autorLivro)) {
                            System.out.println(
                                    "Livro " + catalogoLivros.getListarLivros().get(i).getTitulo() + " Encontrado!");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Livro nao encontrado!");
                    }
                    break;

                case 10:

                    encontrado = false;
                    System.out.println("Informeo ISBN do Livro que deseja buscar: ");
                    String isbnLivro = sc.nextLine();
                    for (int i = 0; i < catalogoLivros.getListarLivros().size(); i++) {
                        if (catalogoLivros.getListarLivros().get(i).getIsnb().equalsIgnoreCase(isbnLivro)) {
                            System.out.println(
                                    "Livro " + catalogoLivros.getListarLivros().get(i).getTitulo() + " Encontrado!");
                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("livro nao encontrado!");
                    }

                    break;

                case 11:
                    catalogoLivros.listarLivros();
                    break;

                case 12:

                    Emprestimo emprestimo = new Emprestimo();
                    Cliente clienteEmprestimo = new Cliente();
                    Livro livroEmprestimo = new Livro();
                    System.out.println("Adicionando emprestimo...");
                    System.out.println("Informe o nome do Cliente:");
                    clienteEmprestimo.setNome(sc.nextLine());
                    emprestimo.setCliente(clienteEmprestimo);
                    System.out.println("Informe o livro que deseja adicionar:");
                    livroEmprestimo.setTitulo(sc.nextLine());
                    emprestimo.setLivro(livroEmprestimo);
                    emprestimo.setDataEmprestimo();
                    gerenciamentoEmprestimos.registrarEmprestimo(emprestimo);
                    break;

                case 13:

                    System.out.println("Informe o livro que sera devolvido: ");
                    String livroDevolvido = sc.nextLine();
                    boolean devolvido = false;

                    for (int i = 0; i < gerenciamentoEmprestimos.getListaEmprestimos().size(); i++) {
                        Emprestimo nEmprestimo = gerenciamentoEmprestimos.getListaEmprestimos().get(i);

                        if (nEmprestimo.getLivro().getTitulo().equalsIgnoreCase(livroDevolvido)) {
                            gerenciamentoEmprestimos.registrarDevolucao(nEmprestimo);
                            devolvido = true;
                        }
                    }

                    if (!devolvido) {
                        System.out.println("Livro nao identificado!");
                        devolvido = false;
                    }

                    sc.nextLine();
                    break;

                case 14:
                    System.out.println("Informe o nome do Livro que deseja verificar: ");
                    String verificarLivro = sc.nextLine();
                    boolean disponivel = false;

                    for (int i = 0; i < gerenciamentoEmprestimos.getListaEmprestimos().size(); i++) {
                        Emprestimo dEmprestimo = gerenciamentoEmprestimos.getListaEmprestimos().get(i);

                        if (dEmprestimo.getLivro().getTitulo().equalsIgnoreCase(verificarLivro)) {
                            gerenciamentoEmprestimos.verificarDisponibilidade(dEmprestimo);
                            disponivel = true;
                        }
                    }

                    if (!disponivel) {
                        System.out.println("Emprestimo disponivel!");
                    }
                    break;

                case 15:
                    System.out.println("Listando emprestimo...");
                    gerenciamentoEmprestimos.listarEmprestimos();
                    break;

                case 16:
                    System.out.println("Volte sempre a nossa Biblioteca!");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente!");
                    break;
            }
        }
        sc.close();
    }
}
