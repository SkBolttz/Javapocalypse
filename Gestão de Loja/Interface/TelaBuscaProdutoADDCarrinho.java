import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaBuscaProdutoADDCarrinho extends JFrame {

    public TelaBuscaProdutoADDCarrinho() {
        setTitle("Busca de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(400, 200);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); 

        JLabel labelId = new JLabel("ID do Produto:");
        JTextField textId = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoCancelar = new JButton("Cancelar");

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);
        Color buscarCorFundo = new Color(70, 130, 180); 
        Color cancelarCorFundo = Color.RED;  
        Color corTextoBotao = Color.WHITE;

        labelId.setFont(fontePadrao);
        textId.setFont(fontePadrao);

        botaoBuscar.setFont(fontePadrao);
        botaoBuscar.setBackground(buscarCorFundo);
        botaoBuscar.setForeground(corTextoBotao);

        botaoCancelar.setFont(fontePadrao);
        botaoCancelar.setBackground(cancelarCorFundo);
        botaoCancelar.setForeground(corTextoBotao);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelId, gbc);

        gbc.gridx = 1;
        add(textId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(botaoBuscar, gbc);

        gbc.gridx = 1;
        add(botaoCancelar, gbc);

        botaoBuscar.addActionListener(e -> {
            try {
                buscarProduto(textId);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        botaoCancelar.addActionListener(e -> dispose());
    }

    private void buscarProduto(JTextField textId) throws IOException {
        if (textId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha o campo de ID para buscar!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Produto produto = new Produto();
            produto.setId(Integer.parseInt(textId.getText()));
            ArquivoProdutos arquivoProduto = new ArquivoProdutos();
            produto = arquivoProduto.leituraProdutosId(produto.getId(), "Lista Produtos.CSV");

            if(produto != null){
                JOptionPane.showMessageDialog(this,
                        "Produto encontrado!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                TelaAdicionarItemCarrinho telaAdicionarItemCarrinho = new TelaAdicionarItemCarrinho(produto, null, null);
                telaAdicionarItemCarrinho.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,
                        "Produto nao encontrado!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira um ID de produto valido.",   
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaBuscaProdutoADDCarrinho telaBuscaProduto = new TelaBuscaProdutoADDCarrinho();
            telaBuscaProduto.setVisible(true);
        });
    }
}
