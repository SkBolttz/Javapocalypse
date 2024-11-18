package WProjetoPoo;

public class Financas {

    private String nome;
    private String descricao;
    private double valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            if (nome != null) {
                this.nome = nome;
            } else {
                throw new ExcecaoNula("Nao pode ser nulo!");
            }
        } catch (ExcecaoNula e) {
            System.out.println(e.getMessage());
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try {
            if (descricao != null) {
                this.descricao = descricao;
            } else {
                throw new ExcecaoNula("Nao pode ser nulo!");
            }
        } catch (ExcecaoNula e) {
            System.out.println(e.getMessage());
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        try {
            if (valor >= 0) {
                this.valor = valor;
            } else {
                throw new ExcecaoValor("Nao pode ser negativo!");
            }
        } catch (ExcecaoValor e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Nome: " + nome + "\n" +
                "Descrição: " + descricao + "\n" +
                String.format("Valor: R$%.2f", valor);
    }
}
