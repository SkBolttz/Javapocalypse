import java.time.LocalDate;
import java.util.List;

public class Pedido {

    private int id;
    private List<Produto> produtos;
    private Cliente cliente;
    private LocalDate dataDoPedido;
    private String formaDePagamento;
    private boolean pedidoPago = false;
    private StatusDoPedido statusDoPedido;
    private int quantidade;
    private int notaFiscal;

    public Pedido() {

    }

    public Pedido(List<Produto> produtos, Cliente cliente, LocalDate dataDoPedido, String formaDePagamento,
            boolean pedidoPago, String statusDoPedido) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.dataDoPedido = dataDoPedido;
        this.formaDePagamento = formaDePagamento;
        this.pedidoPago = pedidoPago;
        this.statusDoPedido = StatusDoPedido.valueOf(statusDoPedido);
    }

    public int getNotaFiscal(){
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal){
        this.notaFiscal = notaFiscal;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int  quantidade){
        this.quantidade = quantidade;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produto) {
        if (produtos == null) {
            throw new IllegalArgumentException("A lista de produtos nao pode ser nula.");
        } else {
            this.produtos = produto;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente nao pode ser nulo.");
        } else {
            this.cliente = cliente;
        }
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        if (formaDePagamento == null) {
            throw new IllegalArgumentException("A forma de pagamento nao pode ser nula.");
        } else {
            this.formaDePagamento = formaDePagamento;
        }
    }

    public boolean isPedidoPago() {
        return pedidoPago;
    }

    public void setPedidoPago(boolean pedidoPago) {
        if (pedidoPago == false) {
            System.out.println("O pedido nao foi pago.");
            this.pedidoPago = pedidoPago;
        } else {
            System.out.println("O pedido foi pago.");
            this.pedidoPago = pedidoPago;
        }
    }

    public String getStatusDoPedido() {
        return statusDoPedido.name();
    }

    public void setStatusDoPedido(StatusDoPedido statusDoPedido) {
        if (statusDoPedido == null) {
            throw new IllegalArgumentException("O status do pedido nao pode ser nulo.");
        } else {
            this.statusDoPedido = statusDoPedido;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente.getNome()).append("\n")
                .append("Data do Pedido: ").append(dataDoPedido).append("\n")
                .append("Forma de Pagamento: ").append(formaDePagamento).append("\n")
                .append("Produtos: \n");

        for (Produto produto : produtos) {
            sb.append("- ").append(produto.getNome())
                    .append(" (R$ ").append(produto.getPreco()).append(")\n");
        }
        sb.append("Status do Pedido: ").append(statusDoPedido.name()).append("\n")
                .append("Pedido Pago: ").append(pedidoPago ? "Sim" : "Não");

        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pedido other = (Pedido) obj;
        return this.cliente.equals(other.getCliente());
    }

    public int hashCode() {
        return this.cliente.hashCode();
    }
}
