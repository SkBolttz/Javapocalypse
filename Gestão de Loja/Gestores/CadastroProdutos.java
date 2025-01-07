import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroProdutos {

    private Map<Integer, Produto> produtos = new HashMap<>();
    private List<Produto> produtosList = new ArrayList<>();

    public void incluirProduto(Produto produto) throws ProdutoIdDuplicado {
        if (produtos.containsKey(produto.getId())) {
            throw new ProdutoIdDuplicado("Produto com ID " + produto.getId() + " ja existe no arquivo.");
        }
        produtos.put(produto.getId(), produto);
    }

    public void atualizarProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
     }

    public boolean consultarProdutos(int id) {
        for (Produto p : produtosList) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
