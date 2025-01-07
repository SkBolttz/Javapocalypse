import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TelaRemoverProduto extends JFrame {

    @SuppressWarnings("unused")
    private TelaAdmin telaAdmin;
    public TelaRemoverProduto(TelaAdmin telaAdmin) {

        this.telaAdmin = telaAdmin;
        ArquivoProdutos arquivoProdutos = new ArquivoProdutos();

        setTitle("Remover Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); 

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(70, 130, 180);
        Color buttonTextColor = Color.WHITE;

        JLabel lblProdutoId = new JLabel("ID do Produto:");
        lblProdutoId.setFont(fontePadrao);

        JTextField txtProdutoId = new JTextField(20);
        txtProdutoId.setFont(fontePadrao);

        JButton btnRemover = new JButton("Remover");
        btnRemover.setFont(fontePadrao);
        btnRemover.setBackground(buttonColor);
        btnRemover.setForeground(buttonTextColor);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fontePadrao);
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(buttonTextColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblProdutoId, gbc);

        gbc.gridx = 1;
        add(txtProdutoId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnRemover, gbc);

        gbc.gridx = 1;
        add(btnCancelar, gbc);

        btnRemover.addActionListener(e -> {
            String idProduto = txtProdutoId.getText().trim();
            if (idProduto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira o ID do produto.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int produtoId = Integer.parseInt(idProduto);
                
                arquivoProdutos.carregarProdutosDoArquivo("Lista Produtos.CSV");
                Produto produto = arquivoProdutos.leituraProdutosId(produtoId, "Lista Produtos.CSV");
                
                if (produto != null) {
                    arquivoProdutos.excluirProduto(produtoId, "Lista Produtos.CSV");

                    JOptionPane.showMessageDialog(this,
                            "Produto removido com sucesso.",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                            if(telaAdmin != null){
                                @SuppressWarnings("static-access")
                                List<String> produtosAtualizar = arquivoProdutos.lerArquivo("Lista Produtos.CSV");
                                telaAdmin.atualizarTabela(produtosAtualizar);
                            }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Produto não encontrado.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "O ID do produto deve ser um número válido.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao acessar o arquivo de produtos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        btnCancelar.addActionListener(e -> cancelarOperacao());

        setVisible(true);
    }

    private void cancelarOperacao() {
        dispose(); 
    }
}
