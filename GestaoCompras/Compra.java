package Projeto_Final_Listas;

import java.time.LocalDate;
import java.util.Comparator;

public class Compra implements Comparable<Compra> {

    private String nome;
    private double valor;
    private int contador;
    private LocalDate dataDeCompra;

    public Compra(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    
    public int getContador() {
        return contador;
    }


    public void setContador(int contador) {
        this.contador = contador;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " Valor: " + valor + " Data de compra: " + dataDeCompra;
    }

    static class Ordenacao implements Comparator<Compra> {

        @Override
        public int compare(Compra produto1, Compra produto2) {
            if (produto1.getValor() > produto2.getValor()) {
                return 1;
            } else if (produto1.getValor() < produto2.getValor()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    @Override
    public int compareTo(Compra compra) {
        if (this.getValor() > compra.getValor()) {
            return 1;
        } else if (this.getValor() < compra.getValor()) {
            return -1;
        } else {
            return 0;
        }
    }
}
