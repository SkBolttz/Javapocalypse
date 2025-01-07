import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArquivoPedidos {

    //Utilizando
    public class GeradorNotaFiscal {
        private static int ultimaNotaFiscal = 0; // Sequência global

        public synchronized static int gerarNovaNotaFiscal() {
            return ++ultimaNotaFiscal;
        }
    }

    //Utilizando
    public void gravaPedido(Cliente cliente, Pedido pedido, Produto produto) throws IOException {

        Locale.setDefault(Locale.US);

        pedido.setNotaFiscal(GeradorNotaFiscal.gerarNovaNotaFiscal());

        File file = new File("Nota Fiscal - " + pedido.getNotaFiscal() + " - " + cliente.getNome() + ".CSV");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(
                    "Nota Fiscal, Data do Pedido, Forma de Pagamento, Status do Pedido, Produto, Quantidade, Preco");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.format("%d, %s, %s, %s, %s, %d, %.2f%n",
                    pedido.getNotaFiscal(),
                    pedido.getDataDoPedido(),
                    pedido.getFormaDePagamento(),
                    pedido.getStatusDoPedido(),
                    produto.getNome(),
                    produto.getQuantidade(),
                    produto.getPreco()));
        }
    }

    //Utilizando
    public List<Produto> carregarCarrinhoArquivo(String caminho) throws IOException {
        List<Produto> produtos = new ArrayList<>(); 
        BufferedReader reader = new BufferedReader(new FileReader(caminho));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(",");

            if (campos.length != 4) {
                System.out.println("Linha inválida no arquivo CSV: " + linha);
                continue;
            }

            try {
                int id = Integer.parseInt(campos[0].trim());
                String nome = campos[1].trim();
                double preco = Double.parseDouble(campos[2].trim());
                int quantidade = Integer.parseInt(campos[3].trim());

                Produto produto = new Produto(id, nome, nome, preco, quantidade);
                produtos.add(produto);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter dados na linha: " + linha);
            }
        }
        reader.close();
        return produtos;
    }

    //Utilizando
    public void gravarCarrinho(int id, String nome, double preco, int quantidade, String cliente, String caminho)
            throws IOException {

        Locale.setDefault(Locale.US);
        File file = new File("Pedidos_" + cliente + ".CSV");

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
                writer.println("ID,Nome,Preco,Quantidade");
            }
        }

        @SuppressWarnings("unused")
        List<Produto> produtosExistentes = carregarCarrinhoArquivo(caminho);

        Produto novoProduto = new Produto(id, nome, caminho, preco, quantidade);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(String.format("%d,%s,%.2f,%d%n",
                    novoProduto.getId(),
                    novoProduto.getNome(),
                    novoProduto.getPreco(),
                    novoProduto.getQuantidade()));
        }
    }

    //Utilizando
    public Produto leituraProdutosId(int id, String caminho) {

        File file = new File(caminho);

        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + caminho);
            return null;
        }
        
    //Utilizando
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            boolean isFirstLine = true;

            while ((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length == 4) { 
                    try {
                        int idArquivo = Integer.parseInt(dados[0].trim());
                        if (idArquivo == id) {
                            return new Produto(idArquivo, dados[1].trim(),
                                    linha, Double.parseDouble(dados[2].trim()), Integer.parseInt(dados[3].trim()));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter valores: " + e.getMessage());
                    }
                } else {
                    System.err.println("Formato inválido na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar o arquivo: " + e.getMessage());
        }

        return null;
    }

        //Utilizando
    public void limparCarrinho(String arquivo) throws IOException {

        File inputFile = new File(arquivo);
        File tempFile = new File("tempfile_.csv");

        if (!inputFile.exists()) {
            System.err.println("Arquivo não encontrado: " + arquivo);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String linha;
            boolean isFirstLine = true;

            while ((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    writer.println(linha);
                    isFirstLine = false;
                    continue;
                }
            }
        }

        if (!inputFile.delete()) {
            System.err.println("Erro ao excluir o arquivo original.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Erro ao renomear o arquivo temporário.");
        }
    }

        //Utilizando
    public double calcularTotalPedidos(String caminho) throws IOException {
        List<Produto> produtos = carregarCarrinhoArquivo(caminho);
        double total = 0.0;

        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidade();
        }
        return total;
    }

        //Utilizando
    public void removerUnidade(String caminho, int id, int quantidade) throws IOException {
        File inputFile = new File(caminho);
        File tempFile = new File("tempfile_.csv");

        if (!inputFile.exists()) {
            System.err.println("Arquivo não encontrado: " + caminho);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String linha;
            boolean isFirstLine = true;

            while ((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    writer.println(linha);
                    isFirstLine = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length > 0) {
                    try {
                        int idArquivo = Integer.parseInt(dados[0].trim());
                        if (idArquivo == id) {
                            int novaQuantidade = Integer.parseInt(dados[3].trim()) - quantidade;
                            if (novaQuantidade > 0) {
                                writer.println(idArquivo + "," + dados[1].trim() + ","
                                        + dados[2].trim() + "," + novaQuantidade);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter ID: " + e.getMessage());
                    }
                }
            }
        }
        if (!inputFile.delete()) {
            System.err.println("Erro ao excluir o arquivo original.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Erro ao renomear o arquivo temporário.");
        }
    }
}
