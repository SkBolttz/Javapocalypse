package ProjetoBiblioteca;

import java.util.ArrayList;

public class GerenciamentoDeEmprestimos {

    private ArrayList<Emprestimo> listaEmprestimos = new ArrayList<>();

    public ArrayList<Emprestimo> getListaEmprestimos() {
        return listaEmprestimos;
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        this.listaEmprestimos.add(emprestimo);
        System.out.println("Emprestimo registrado com sucesso");
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        if (listaEmprestimos.remove(emprestimo)) {
            System.out.println("Livro devolvido com sucesso");
        } else {
            System.out.println("Emprestimo nao encontrado");
        }
    }

    public void verificarDisponibilidade(Emprestimo emprestimo) {
        if (emprestimo.getDataDevolucao() != null) {
            System.out.println("Emprestimo disponível!");
        } else {
            System.out.println("Emprestimo indisponível!");
        }
    }

    public void listarEmprestimos() {
        if (listaEmprestimos.isEmpty()) {
            System.out.println("Sem emprestimo registrado!");
        } else {
            for (Emprestimo emprestimo : listaEmprestimos) {
                System.out.println("Emprestimo: Livro " + emprestimo.getLivro().getTitulo() +
                        ", Cliente: - " + emprestimo.getCliente().getNome() +
                        ", Data de emprestimo - " + emprestimo.getDataEmprestimo());
            }
        }
    }
}
