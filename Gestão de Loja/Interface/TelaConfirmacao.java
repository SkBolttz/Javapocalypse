import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaConfirmacao extends JFrame {
    
    private Cliente cliente;
    private TelaPrincipal telaPrincipal;
    @SuppressWarnings("unused")
    private Produto produto;
    public TelaConfirmacao(Cliente cliente, TelaPrincipal telaPrincipal, Produto produto) {
        this.telaPrincipal = telaPrincipal;
        this.cliente = cliente;
        this.produto = produto;
        
        setTitle("Limpar Carrinho");
        setSize(300, 150);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Você deseja limpar o carrinho?", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> {
            try {
                limparCarrinho(cliente); 
                atualizarTelas();
                JOptionPane.showMessageDialog(this, "Carrinho limpo!");
                dispose(); 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao limpar o carrinho!");
                ex.printStackTrace();
            }
        });

        panel.add(btnConfirmar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            JOptionPane.showMessageDialog(TelaConfirmacao.this, "Ação cancelada!");
            dispose(); 
        });
        panel.add(btnCancelar);
        
        add(panel, BorderLayout.SOUTH);
    }
    
    public void limparCarrinho(Cliente cliente) throws IOException {
        ArquivoPedidos arquivoPedidos = new ArquivoPedidos();
        arquivoPedidos.limparCarrinho("Pedidos_" + cliente.getNome() + ".CSV");
    }

    public void atualizarTelas() {
        try {
            if (telaPrincipal != null) {
                telaPrincipal.atualizarPreco(cliente);
                telaPrincipal.carregarProdutosIniciais();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar as telas!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaConfirmacao(null, null, null).setVisible(true);
            }
        });
    }
}
