import java.util.Objects;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    public Produto(int id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null) {
            System.out.println("Nome do cliente não pode ser nulo");
            return;
        } else {
            this.nome = nome;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null) {
            System.out.println("Descrição do produto não pode ser nulo");
            return;
        } else {
            this.descricao = descricao;
        }
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            System.out.println("Preço do produto deve ser maior ou igual a zero");
            return;
        } else {
            this.preco = preco;
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            System.out.println("Quantidade do produto deve ser maior ou igual a zero");
            return;
        } else {
            this.quantidade = quantidade;
        }
    }

    @Override
    public String toString() {
        return "Produto {" +
                "\n  ID: " + id +
                "\n  Nome: " + nome +
                "\n  Descrição: " + descricao +
                "\n  Preço: R$ " + String.format("%.2f", preco) +
                "\n  Quantidade: " + quantidade +
                "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; 
        }
        Produto other = (Produto) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int compararPorId(Produto p1, Produto p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }
}