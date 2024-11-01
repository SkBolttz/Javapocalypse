package ProjetoBiblioteca;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
public class Emprestimo {

    private Livro livro;
    private Cliente cliente;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataEmprestimo() {
        this.dataEmprestimo = LocalDate.now();
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = LocalDate.now();
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void registrarEmprestimo() {
        if (this.livro == null || this.cliente == null) {
            throw new IllegalArgumentException("Livro e Cliente devem ser mencionados:");
        }
        this.dataEmprestimo = LocalDate.now();
        System.out.println("Emprestimo registrado com sucesso");
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        if (dataDevolucao == null) {
            throw new IllegalArgumentException("Data de devolução deve ser informada");
        }

        if (dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolução deve ser posterior a data de empréstimo");
        }

        this.dataDevolucao = LocalDate.now();
        System.out.println("Livro devolvido com sucesso");
    }

    public void calcularDiasEmprestados() {
        if (dataDevolucao == null) {
            throw new NullPointerException("Data de devolucao precisa ser registrada!");
        }

        Duration duracao = Duration.between(dataEmprestimo, dataDevolucao);
        long dias = duracao.toDays();
        System.out.println("O livro ficou emprestado por " + dias + " dias");
    }
}
