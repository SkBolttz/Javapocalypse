import java.util.ArrayList;
import java.util.List;

public class GestaoPedidos {

    private List<Produto> produtos = new ArrayList<>();
    private List<Pedido> carrinho = new ArrayList<>();

    //Utilizando
    public void incluirCarrinho(Produto pedido) {
        produtos.add(pedido);
    }

        //Utilizando
    public void adicionarCarrinho(Produto produto, Pedido pedido) {
        if (produto == null || pedido == null) {
            System.out.println("Produto ou pedido inválido!");
            return;
        }

        if (!produtos.contains(produto)) {
            System.out.println("Produto inválido!");
            return;
        }

        pedido.getProdutos().add(produto);
        if (!carrinho.contains(pedido)) {
            carrinho.add(pedido); 
        }

        System.out.println("Produto adicionado ao carrinho.");
    }

        //Utilizando
    public void excluirCarrinho(Produto produto, Pedido pedido) {
        if (pedido == null || produto == null || !carrinho.contains(pedido)) {
            System.out.println("Pedido ou produto não encontrado no carrinho!");
            return;
        }

        if (pedido.getProdutos().remove(produto)) {
            System.out.println("Produto removido do pedido com sucesso!");
            if (pedido.getProdutos().isEmpty()) {
                carrinho.remove(pedido); 
                System.out.println("Pedido vazio removido do carrinho.");
            }
        } else {
            System.out.println("Produto não encontrado no pedido!");
        }
    }
}
