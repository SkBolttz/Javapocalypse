
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ArquivoProdutos {

        //Utilizando
    public void gravarProduto(int id, String nome, String descricao, double preco, int quantidade, String caminho)
            throws IOException, ProdutoIdDuplicado {

        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
                writer.println("ID,Nome,Descricao,Preco,Quantidade");
            }
        }

        Locale.setDefault(Locale.US);

        List<Produto> produtosExistentes = carregarProdutosDoArquivo(caminho);

        for (Produto p : produtosExistentes) {
            if (p.getId() == id) {
                throw new ProdutoIdDuplicado("Produto com ID " + id + " já existe no arquivo.");
            }
        }

        Produto novoProduto = new Produto(id, nome, descricao, preco, quantidade);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(String.format("%d,%s,%s,%.2f,%d%n",
                    novoProduto.getId(),
                    novoProduto.getNome(),
                    novoProduto.getDescricao(),
                    novoProduto.getPreco(),
                    novoProduto.getQuantidade()));
        }
    }

        //Utilizando
    public List<Produto> carregarProdutosDoArquivo(String caminho) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminho));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(",");

            if (campos.length != 5) {
                System.out.println("Linha inválida no arquivo CSV: " + linha);
                continue;
            }

            try {
                int id = Integer.parseInt(campos[0].trim());
                String nome = campos[1].trim();
                String descricao = campos[2].trim();
                double preco = Double.parseDouble(campos[3].trim());
                int quantidade = Integer.parseInt(campos[4].trim());

                Produto produto = new Produto(id, nome, descricao, preco, quantidade);
                produtos.add(produto);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter dados na linha: " + linha);
            }
        }
        reader.close();
        return produtos;
    }

        //Utilizando
    public Produto leituraProdutosId(int id, String caminho) {

        File file = new File(caminho);

        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + caminho);
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            boolean isFirstLine = true;

            while ((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length >= 1) {
                    try {
                        int idArquivo = Integer.parseInt(dados[0].trim());
                        if (idArquivo == id) {
                            return new Produto(idArquivo, dados[1].trim(), dados[2].trim(),
                                    Double.parseDouble(dados[3].trim()), Integer.parseInt(dados[4].trim()));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter ID: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar o arquivo: " + e.getMessage());
        }

        return null;
    }

        //Utilizando
    public void excluirProduto(int idProduto, String arquivo) throws IOException {

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
                // Ignorar cabeçalho
                if (isFirstLine) {
                    writer.println(linha);
                    isFirstLine = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length > 0) {
                    try {
                        int idArquivo = Integer.parseInt(dados[0].trim());
                        if (idArquivo != idProduto) { 
                            writer.println(linha);
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

        //Utilizando
    public void atualizarProduto(int idProduto, String nome, String descricao, double preco, int quantidade,
            String caminho) throws IOException {

        List<Produto> produtos = carregarProdutosDoArquivo(caminho);
        boolean produtoAtualizado = false;

        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuantidade(quantidade);
                produtoAtualizado = true;
                break;
            }
        }

        if (produtoAtualizado) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
                writer.println("Id,Nome,Descricao,Preco,Quantidade");
                for (Produto produto : produtos) {
                    writer.printf("%d,%s,%s,%.2f,%d%n", produto.getId(), produto.getNome(), produto.getDescricao(),
                            produto.getPreco(), produto.getQuantidade());
                }
            }
        } else {
            System.out.println("Produto com ID " + idProduto + " não encontrado.");
        }
    }

        //Utilizando
    public Produto consultarProduto(int idProduto, String caminho) throws IOException {

        List<Produto> produtos = carregarProdutosDoArquivo(caminho);

        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        return null;
    }

        //Utilizando
    public static List<String> lerArquivo(String caminhoArquivo) {

        List<String> linhas = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        Collections.sort(linhas);
        return linhas;
    }

        //Utilizando
    public List<Produto> atualizarTabela(List<Produto> produtos2) {

        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("produtos.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Produto produto = new Produto(Integer.parseInt(dados[0]), dados[1], linha, Double.parseDouble(dados[2]),
                        Integer.parseInt(dados[3]));
                produtos.add(produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
