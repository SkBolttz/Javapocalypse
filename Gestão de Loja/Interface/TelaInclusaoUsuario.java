import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaInclusaoUsuario extends JFrame {
    
    @SuppressWarnings("unused")
    private Cliente cliente;
    public TelaInclusaoUsuario(Cliente cliente) {

        this.cliente = cliente;
        setTitle("Adicionar ao Carrinho");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(400, 200);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); 

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

        JButton salvarButton = criarBotao("Salvar", buttonColor, Color.WHITE);
        JButton cancelarButton = criarBotao("Cancelar", cancelColor, Color.WHITE);

        adicionarComponente(gbc, idLabel, 0, 0);
        adicionarComponente(gbc, idField, 1, 0);

        adicionarComponente(gbc, salvarButton, 0, 5);
        adicionarComponente(gbc, cancelarButton, 1, 5);

        salvarButton.addActionListener(e -> salvarProduto(idField));

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

    private void salvarProduto(JTextField idField) {
        try {
            String idTexto = idField.getText().trim();
            if (idTexto.isEmpty() || !idTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira um ID de produto válido.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            int idProduto = Integer.parseInt(idTexto);
    
            ArquivoProdutos arquivoProdutos = new ArquivoProdutos();
            arquivoProdutos.carregarProdutosDoArquivo("Lista Produtos.CSV");

            Produto produto = arquivoProdutos.consultarProduto(idProduto, "Lista Produtos.CSV");
            if (produto != null) {
    
                GestaoPedidos gestaoPedidos = new GestaoPedidos();
                CadastroProdutos cadastroProdutos = new CadastroProdutos();

                cadastroProdutos.incluirProduto(produto);
                gestaoPedidos.incluirCarrinho(produto);
    
                JOptionPane.showMessageDialog(this,
                        "Produto adicionado ao carrinho com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Produto não encontrado!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao acessar o arquivo: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocorreu um erro inesperado: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
