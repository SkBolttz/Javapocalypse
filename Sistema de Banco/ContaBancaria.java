package BankingSystem;
class ContaBancaria {

// Criação da Classe ContaBancaria

    private String numero;
    private String titular;
    private double saldo;
   
    public void setNumero(String numero){
        this.numero = numero;
    }
    public String getNumero(){
        return numero;
    }
   
    public void setTitular(String titular){
        this.titular = titular;
    }
    public String getTitular(){
        return titular;
    }
   
    public double getSaldo(){
        return saldo;
    }
   
    public void depositar(double valor){
        if(valor < 0){
            System.out.println("Tentativa de depósito inválida!");
        }else{
        saldo = saldo + valor;
    }
    }
   
    public void sacar(double valor){
        if(valor < 0){
            System.out.println("Tentativa de saque inválido!");
        }else if(saldo - valor < 0){
            System.out.println("Tentativa de saque inválido!");
        }else{
        saldo = saldo - valor;
        }        
    }
   
    public void transferir(ContaBancaria contaDestino, double valor){
        this.saldo = this.saldo - valor;
        contaDestino.depositar(valor);
    
    }
    }
