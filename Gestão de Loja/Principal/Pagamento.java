import java.time.LocalDate;

public class Pagamento {

    private int id;
    private double valorTotal;
    private Pedido pedido;
    private LocalDate dataDoPagamento;
    private String formaDePagamento;

    public Pagamento() {

    }

    public Pagamento(double valorTotal, Pedido pedido, LocalDate dataDoPagamento, String formaDePagamento) {
        setValorTotal(valorTotal);
        this.pedido = pedido;
        this.dataDoPagamento = dataDoPagamento;
        setFormaDePagamento(formaDePagamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor total deve ser maior ou igual a zero.");
        } else {
            this.valorTotal = valorTotal;
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        } else {
            this.pedido = pedido;
        }
    }

    public LocalDate getDataDoPagamento() {
        return dataDoPagamento;
    }

    public void setDataDoPagamento(LocalDate dataDoPagamento) {
        if (dataDoPagamento == null) {
            throw new IllegalArgumentException("A data do pagamento nao pode ser nula.");
        } else {
            this.dataDoPagamento = dataDoPagamento;
        }
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

    @Override
    public String toString() {
        return "Pagamento{" +
                "valorTotal=" + valorTotal +
                ", pedido=" + (pedido != null ? pedido.toString() : "null") +
                ", dataDoPagamento=" + dataDoPagamento +
                ", formaDePagamento='" + formaDePagamento + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pagamento other = (Pagamento) obj;
        return this.pedido.equals(other.getPedido()) && this.getValorTotal() == other.getValorTotal();
    }

    public int hashCode() {
        return Double.hashCode(this.getValorTotal());
    }
}
