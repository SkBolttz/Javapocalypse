import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TelaPagamento extends JFrame {

    @SuppressWarnings("unused")
    private Pedido pedido;
    @SuppressWarnings("unused")
    private Cliente cliente;
    @SuppressWarnings("unused")
    private Produto produto;
    @SuppressWarnings("unused")
    private static TelaPrincipal telaPrincipal;
            
                @SuppressWarnings("static-access")
                public TelaPagamento(Cliente cliente, Pedido pedido, Produto produto, TelaPrincipal telaPrincipal) {
            
                    this.cliente = cliente;
                    this.pedido = pedido;
                    this.produto = produto;
                    this.telaPrincipal = telaPrincipal;
        
                setTitle("Tela de Pagamento");
                setSize(600, 500); 
                setLocationRelativeTo(null);
                setLayout(new BorderLayout());
        
                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBackground(new Color(255, 255, 255)); 
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(15, 15, 15, 15); 
                gbc.fill = GridBagConstraints.HORIZONTAL;
        
                Color buttonColor = new Color(0, 86, 179); 

                JLabel titularLabel = new JLabel("Titular:");
                titularLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 0;
                mainPanel.add(titularLabel, gbc);
        
                JTextField titularField = new JTextField(20); 
                titularField.setPreferredSize(new Dimension(300, 30)); 
                gbc.gridx = 1;
                gbc.gridy = 0;
                mainPanel.add(titularField, gbc);
        
                JLabel cpfCnpjLabel = new JLabel("CPF/CNPJ:");
                cpfCnpjLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 1;
                mainPanel.add(cpfCnpjLabel, gbc);
        
                JTextField cpfCnpjField = new JTextField(20); 
                cpfCnpjField.setPreferredSize(new Dimension(300, 30));
                gbc.gridx = 1;
                gbc.gridy = 1;
                mainPanel.add(cpfCnpjField, gbc);
        
                JLabel numeroCartaoLabel = new JLabel("Número do Cartão:");
                numeroCartaoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 2;
                mainPanel.add(numeroCartaoLabel, gbc);
        
                JTextField numeroCartaoField = new JTextField(20); 
                numeroCartaoField.setPreferredSize(new Dimension(300, 30)); 
                gbc.gridx = 1;
                gbc.gridy = 2;
                mainPanel.add(numeroCartaoField, gbc);
        
                // Validade
                JLabel validadeLabel = new JLabel("Validade (MM/AA):");
                validadeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 3;
                mainPanel.add(validadeLabel, gbc);
        
                JTextField validadeField = new JTextField(20); 
                validadeField.setPreferredSize(new Dimension(300, 30)); 
                gbc.gridx = 1;
                gbc.gridy = 3;
                mainPanel.add(validadeField, gbc);
        
                // CVV
                JLabel cvvLabel = new JLabel("Código (CVV):");
                cvvLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 4;
                mainPanel.add(cvvLabel, gbc);
        
                JTextField cvvField = new JTextField(20); 
                cvvField.setPreferredSize(new Dimension(300, 30)); 
                gbc.gridx = 1;
                gbc.gridy = 4;
                mainPanel.add(cvvField, gbc);
        
                // Parcelas
                JLabel parcelasLabel = new JLabel("Parcelas:");
                parcelasLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 5;
                mainPanel.add(parcelasLabel, gbc);
        
                String[] parcelasOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
                JComboBox<String> parcelasCombo = new JComboBox<>(parcelasOptions);
                gbc.gridx = 1;
                gbc.gridy = 5;
                mainPanel.add(parcelasCombo, gbc);
        
                JButton finalizarButton = new JButton("Finalizar");
                finalizarButton.setBackground(buttonColor);
                finalizarButton.setForeground(Color.WHITE);
                finalizarButton.setFont(new Font("Arial", Font.BOLD, 14));
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                mainPanel.add(finalizarButton, gbc);
        
                finalizarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                
                        if (titularField.getText().isEmpty() || cpfCnpjField.getText().isEmpty()
                                || numeroCartaoField.getText().isEmpty() || validadeField.getText().isEmpty()
                                || cvvField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.", "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                
                        try {
                            ArquivoPedidos arquivoPedidos = new ArquivoPedidos();
                
                            pedido.setDataDoPedido(LocalDate.now());
                            pedido.setFormaDePagamento("Cartão de Crédito");
                            pedido.setStatusDoPedido(StatusDoPedido.FINALIZADO);
                
                            List<Produto> produtos = arquivoPedidos.carregarCarrinhoArquivo("Pedidos_" + cliente.getNome() + ".CSV");
                
                            for (Produto p : produtos) {
                                arquivoPedidos.gravaPedido(cliente, pedido, p);
                            }                                  
                
                            arquivoPedidos.limparCarrinho("Pedidos_" + cliente.getNome() + ".CSV");
                
                            telaPrincipal.atualizarPreco(cliente);
                            telaPrincipal.carregarProdutosIniciais();
                
                            JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso! A nota fiscal foi gerada.",
                                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                            dispose();
                
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao processar o pedido: " + ex.getMessage(),
                                    "Erro", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                });
        
                add(mainPanel, BorderLayout.CENTER);
            }
        
            public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Cliente cliente = new Cliente();
                        Pedido pedido = new Pedido();
                        Produto produto = new Produto();
                        try {
                            telaPrincipal = new TelaPrincipal(cliente, null, null, pedido, produto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        new TelaPagamento(cliente, pedido, produto, telaPrincipal).setVisible(true);
            }
        });
    }
}
