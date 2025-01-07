import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaConsultaProduto extends JFrame {

    public TelaConsultaProduto(Produto produto) {
        setTitle("Consulta de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(600, 500);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); 

        JLabel labelId = new JLabel("ID do Produto:");
        JTextField textId = new JTextField(20);
        JButton botaoConsultar = new JButton("Consultar");
        JButton botaoCancelar = new JButton("Cancelar");

        JTextArea textAreaProduto = new JTextArea(10, 20);
        textAreaProduto.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(textAreaProduto); 

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);
        Color consultarCorFundo = new Color(70, 130, 180); 
        Color cancelarCorFundo = Color.RED; 
        Color corTextoBotao = Color.WHITE; 

        labelId.setFont(fontePadrao);
        textId.setFont(fontePadrao);

        botaoConsultar.setFont(fontePadrao);
        botaoConsultar.setBackground(consultarCorFundo);
        botaoConsultar.setForeground(corTextoBotao);

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
        add(botaoConsultar, gbc);

        gbc.gridx = 1;
        add(botaoCancelar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        botaoConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produto = new Produto();
                ArquivoProdutos arquivoProdutos = new ArquivoProdutos();

                if (textId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(TelaConsultaProduto.this,
                            "Por favor, preencha o campo de ID para consultar!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    produto.setId(Integer.parseInt(textId.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaConsultaProduto.this,
                            "ID do produto inválido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                produto.setId(Integer.parseInt(textId.getText()));

                    Produto produtoConsultado = arquivoProdutos.leituraProdutosId(produto.getId(), "Lista Produtos.CSV");
        
                    if (produtoConsultado != null) {
                        textAreaProduto.setText("ID: " + produtoConsultado.getId() + "\n");
                        textAreaProduto.append("Nome: " + produtoConsultado.getNome() + "\n");
                        textAreaProduto.append("Descrição: " + produtoConsultado.getDescricao() + "\n");
                        textAreaProduto.append("Preço: R$ " + produtoConsultado.getPreco() + "\n");
                        textAreaProduto.append(String.format("Quantidade: %d\n", produtoConsultado.getQuantidade()));

                    } else {
                        JOptionPane.showMessageDialog(TelaConsultaProduto.this,
                                "Produto não encontrado!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        textAreaProduto.setText("");
                    }
                }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaConsultaProduto telaConsultaProduto = new TelaConsultaProduto(new Produto());
            telaConsultaProduto.setVisible(true);
        });
    }
}
