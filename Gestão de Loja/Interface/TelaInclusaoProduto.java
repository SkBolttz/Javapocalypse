import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TelaInclusaoProduto extends JFrame {

    @SuppressWarnings("unused")
    private TelaAdmin telaAdmin;

    public TelaInclusaoProduto(TelaAdmin telaAdmin) {

        this.telaAdmin = telaAdmin;
        setTitle("Inclusão de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); 

        // Definição de cores
        Color backgroundColor = new Color(230, 240, 255);
        Color buttonColor = new Color(70, 130, 180);
        Color cancelColor = new Color(200, 50, 50);
        Color textColor = Color.BLACK;

        getContentPane().setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = criarLabel("ID do Produto:", textColor);
        JTextField idField = criarTextField();

        JLabel nomeLabel = criarLabel("Nome do Produto:", textColor);
        JTextField nomeField = criarTextField();

        JLabel descricaoLabel = criarLabel("Descrição:", textColor);
        JTextArea descricaoField = criarTextArea();
        JScrollPane scrollDescricao = new JScrollPane(descricaoField);

        JLabel precoLabel = criarLabel("Preço:", textColor);
        JTextField precoField = criarTextField();

        JLabel quantidadeLabel = criarLabel("Quantidade:", textColor);
        JTextField quantidadeField = criarTextField();

        JButton salvarButton = criarBotao("Salvar", buttonColor, Color.WHITE);
        JButton cancelarButton = criarBotao("Cancelar", cancelColor, Color.WHITE);

        adicionarComponente(gbc, idLabel, 0, 0);
        adicionarComponente(gbc, idField, 1, 0);

        adicionarComponente(gbc, nomeLabel, 0, 1);
        adicionarComponente(gbc, nomeField, 1, 1);

        adicionarComponente(gbc, descricaoLabel, 0, 2);
        adicionarComponente(gbc, scrollDescricao, 1, 2);

        adicionarComponente(gbc, precoLabel, 0, 3);
        adicionarComponente(gbc, precoField, 1, 3);

        adicionarComponente(gbc, quantidadeLabel, 0, 4);
        adicionarComponente(gbc, quantidadeField, 1, 4);

        adicionarComponente(gbc, salvarButton, 0, 5);
        adicionarComponente(gbc, cancelarButton, 1, 5);

        salvarButton
                .addActionListener(e -> salvarProduto(idField, nomeField, descricaoField, precoField, quantidadeField));

        cancelarButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private JLabel criarLabel(String texto, Color textColor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(textColor);
        return label;
    }

    private JTextField criarTextField() {
        return new JTextField(20);
    }

    private JTextArea criarTextArea() {
        JTextArea textArea = new JTextArea(3, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JButton criarBotao(String texto, Color background, Color foreground) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(background);
        button.setForeground(foreground);
        return button;
    }

    private void adicionarComponente(GridBagConstraints gbc, Component componente, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(componente, gbc);
    }

    private void salvarProduto(JTextField idField, JTextField nomeField, JTextArea descricaoField,
            JTextField precoField, JTextField quantidadeField) {

        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        Produto produto = new Produto();
        ArquivoProdutos arquivoProdutos = new ArquivoProdutos();

        try {
            if (nomeField.getText().trim().isEmpty() || descricaoField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome e Descrição são obrigatórios.");
            }

            String idText = idField.getText().trim();
            String precoText = precoField.getText().trim();
            String quantidadeText = quantidadeField.getText().trim();

            if (idText.isEmpty() || precoText.isEmpty() || quantidadeText.isEmpty()) {
                throw new IllegalArgumentException("ID, Preço e Quantidade não podem ser vazios.");
            }

            int id = Integer.parseInt(idText); 
            double preco = Double.parseDouble(precoText);
            int quantidade = Integer.parseInt(quantidadeText);

            produto.setId(id);
            produto.setNome(nomeField.getText().trim());
            produto.setDescricao(descricaoField.getText().trim());
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);

            cadastroProdutos.incluirProduto(produto);
            arquivoProdutos.gravarProduto(produto.getId(), produto.getNome(), produto.getDescricao(),
                    produto.getPreco(), produto.getQuantidade(), "Lista Produtos.CSV");

            JOptionPane.showMessageDialog(this,
                    "Produto salvo com sucesso:\n" +
                            "ID: " + produto.getId() + "\n" +
                            "Nome: " + produto.getNome() + "\n" +
                            "Descrição: " + produto.getDescricao() + "\n" +
                            "Preço: " + produto.getPreco() + "\n" +
                            "Quantidade: " + produto.getQuantidade(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            if(telaAdmin != null){
                @SuppressWarnings("static-access")
                List<String> produtosAtualizar = arquivoProdutos.lerArquivo("Lista Produtos.CSV");
                telaAdmin.atualizarTabela(produtosAtualizar);
            }
            // Limpando campos
            idField.setText("");
            nomeField.setText("");
            descricaoField.setText("");
            precoField.setText("");
            quantidadeField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "ID, Preço e Quantidade devem ser valores numéricos válidos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o produto: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ProdutoIdDuplicado ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
