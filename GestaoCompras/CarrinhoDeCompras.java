package Projeto_Final_Listas;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {

    private List<Compra> compras = new ArrayList<>();
    private double limite;
    private double saldo;
    private int contador = 0;

    public CarrinhoDeCompras(double limite) {
        this.limite = limite;
        this.saldo = 0;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void adicionarCompra(Compra compra) {
        if (compras.isEmpty() || compra.getValor() < 0) {
            System.out.println("O valor da compra deve ser positivo!");
        }
        if (this.getSaldo() + compra.getValor() > this.getLimite()) {
            System.out.println("Limite ultrapassado");
        } else {
            this.setSaldo((this.getSaldo() + compra.getValor()));
            compras.add(compra);
            compra.setContador(contador++);
        }
    }

    public void removerCompra(Compra compra) {
        if (compras.isEmpty()) {
            System.out.println("Carrinho de compras vazio");
            return;
        }

        if (this.getSaldo() - compra.getValor() < 0) {
            System.out.println("Saldo Saldo não pode ficar negativo!");
            return;
        }

        String nome = compra.getNome();
        for (int i = compras.size() - 1; i >= 0; i--) {
            if (compras.get(i).getNome().equals(nome) || compras.get(i).getContador() == compra.getContador()) {
                Compra compraRemovida = compras.get(i);
                this.setSaldo(this.getSaldo() - compraRemovida.getValor());
                compras.remove(i);
                break;
            }
        }
    }

    public void calcularValor() {
        System.out.println("Limite: " + this.getLimite() + "\n" +
                "Saldo Gasto: " + this.getSaldo());
    }
}