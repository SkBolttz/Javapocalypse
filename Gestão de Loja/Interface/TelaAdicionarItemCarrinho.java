import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaAdicionarItemCarrinho extends JFrame {
    @SuppressWarnings("unused")
    private Produto produto;
    @SuppressWarnings("unused")
    private Cliente cliente;
    @SuppressWarnings("unused")
    private TelaPrincipal telaPrincipal;

    public TelaAdicionarItemCarrinho(Produto produto, Cliente cliente, TelaPrincipal telaPrincipal) throws IOException {

        this.produto = produto;
        this.cliente = cliente;
        this.telaPrincipal = telaPrincipal;

         setTitle("Adicionar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null); 

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 150, 25);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(180, 20, 180, 25);
        add(txtNome);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(20, 60, 150, 25);
        add(lblDescricao);

        JTextField txtDescricao = new JTextField();
        txtDescricao.setEnabled(false);
        txtDescricao.setBounds(180, 60, 180, 25);
        add(txtDescricao);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 100, 150, 25);
        add(lblPreco);

        JTextField txtPreco = new JTextField();
        txtPreco.setEnabled(false);
        txtPreco.setBounds(180, 100, 180, 25);
        add(txtPreco);

        JLabel lblQuantidade = new JLabel("Quantidade para Comprar:");
        lblQuantidade.setBounds(20, 180, 150, 25);
        add(lblQuantidade);

        JTextField txtQuantidade = new JTextField();
        txtQuantidade.setBounds(180, 180, 180, 25);
        add(txtQuantidade);

        JLabel lblQuantidadeDisponivel = new JLabel("Quantidade Disponível:");
        lblQuantidadeDisponivel.setBounds(20, 140, 150, 25);
        add(lblQuantidadeDisponivel);

        JTextField txtQuantidadeDisponivel = new JTextField();
        txtQuantidadeDisponivel.setEnabled(false);
        txtQuantidadeDisponivel.setBounds(180, 140, 180, 25);
        add(txtQuantidadeDisponivel);

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(80, 220, 100, 30);
        add(btnIncluir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 220, 100, 30);
        add(btnCancelar);

        txtNome.setText(produto.getNome());
        txtDescricao.setText(produto.getDescricao());
        txtPreco.setText(String.valueOf(produto.getPreco()));
        txtQuantidadeDisponivel.setText(String.valueOf(produto.getQuantidade()));

        telaPrincipal.carregarProdutosIniciais();

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantidadeX = 0;
                quantidadeX = Integer.parseInt(txtQuantidadeDisponivel.getText());

                GestaoPedidos gestaoPedidos = new GestaoPedidos();
                Pedido pedido = new Pedido();
                ArquivoPedidos arquivoPedidos = new ArquivoPedidos();

                produto.setNome(txtNome.getText());
                produto.setDescricao(txtDescricao.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

                pedido.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                pedido.setId(cliente.getId());

                gestaoPedidos.adicionarCarrinho(produto, pedido);

                if (pedido.getQuantidade() <= 0) {
                    JOptionPane.showMessageDialog(TelaAdicionarItemCarrinho.this,
                            "Quantidade não pode ser menor ou igual a 0!");
                    return;
                } else if (pedido.getQuantidade() > quantidadeX) {
                    JOptionPane.showMessageDialog(TelaAdicionarItemCarrinho.this, "Quantidade indisponível!");
                    txtQuantidade.setText(""); 
                    return;
                } else {
                    JOptionPane.showMessageDialog(TelaAdicionarItemCarrinho.this,
                            "Produto adicionado ao carrinho com sucesso!");
                }

                try {
                    arquivoPedidos.gravarCarrinho(produto.getId(), produto.getNome(), produto.getPreco(),
                            produto.getQuantidade(), cliente.getNome(), "Pedidos_" + cliente.getNome() + ".CSV");
                    telaPrincipal.atualizarPreco(cliente);
                    telaPrincipal.carregarProdutosIniciais();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
