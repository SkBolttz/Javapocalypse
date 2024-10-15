package heranc2;
import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

    private Cliente titular;
    private String numero;
    private double saldo;
    private ArrayList<Movimento> movimento = new ArrayList<>();

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void depositar(double valor) {
        if (valor < 0) {
            System.out.println("Tentativa de depósito inválida");
        } else {
            this.saldo += valor;
        }
    }

    public void sacar(double valor) {
        if (valor < 0) {
            System.out.println("Tentativa de saque inválida");
        } else {
            this.saldo -= valor;
        }
    }

    public void transferir(ContaBancaria c, double valor) {
        if (valor < 0) {
            System.out.println("Tentativa de transferência inválida");
        } else {
            this.saldo -= valor;
            c.depositar(valor);
        }
    }
    
    public List<Movimento> getMovimento(){
        return movimento;
    }
    
    protected void incluirMovimento(Movimento c){
        movimento.add(c);
    }
}
