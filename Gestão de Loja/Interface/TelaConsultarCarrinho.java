import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TelaConsultarCarrinho extends JFrame {

    private JTable tableCarrinho;
    private DefaultTableModel modelCarrinho;
    private Cliente cliente;
    @SuppressWarnings("unused")
    private GestaoPedidos gestaoPedido;
    private JLabel lblTotal;
    @SuppressWarnings("unused")
    private Pedido pedido;
    @SuppressWarnings("unused")
    private Produto produto;
    @SuppressWarnings("unused")
    private TelaPrincipal telaPrincipal;
    public TelaConsultarCarrinho(Cliente cliente, GestaoPedidos gestaoPedido, Pedido pedido, Produto produto, TelaPrincipal telaPrincipal) throws IOException {

        this.pedido = pedido;
        this.gestaoPedido = gestaoPedido;
        this.cliente = cliente;
        this.produto = produto;
        this.telaPrincipal = telaPrincipal;

        setTitle("Consultar Carrinho");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color primaryColor = new Color(72, 133, 237); 
        Color buttonColor = new Color(60, 179, 113); 
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color backgroundColor = new Color(245, 245, 245); 

        getContentPane().setBackground(backgroundColor);

        JPanel panelTop = new JPanel();
        panelTop.setBackground(primaryColor);
        panelTop.setPreferredSize(new Dimension(800, 50));
        JLabel lblTitulo = new JLabel("Carrinho", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelTop.add(lblTitulo);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(backgroundColor);

        String[] columnNames = { "ID", "Produto", "Preco", "Quantidade"};
        modelCarrinho = new DefaultTableModel(columnNames, 0);
        tableCarrinho = new JTable(modelCarrinho);
        tableCarrinho.setFont(new Font("Serif", Font.PLAIN, 17));
        tableCarrinho.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tableCarrinho);
        panelCenter.add(scrollPane, BorderLayout.CENTER);
        modelCarrinho.fireTableDataChanged();

        add(panelCenter, BorderLayout.CENTER);
        lblTotal = new JLabel("Total: R$ 0.00", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCenter.add(lblTotal, BorderLayout.SOUTH); 

        JPanel panelBottom = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBottom.setBackground(backgroundColor);

        JPanel panelCarrinho = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelCarrinho.setBackground(backgroundColor);
        panelCarrinho.setBorder(BorderFactory.createTitledBorder("Gerenciamento do Carrinho"));

        JButton btnVoltar = criarBotao("Voltar", buttonColor, buttonFont);
        JButton btnFinalizarCompra = criarBotao("Finalizar Compra", buttonColor, buttonFont);

        panelCarrinho.add(btnFinalizarCompra);
        panelCarrinho.add(btnVoltar);

        btnVoltar.addActionListener(e -> voltar());
        btnFinalizarCompra.addActionListener(e -> finalizarCompra());

        panelBottom.add(panelCarrinho);

        add(panelBottom, BorderLayout.SOUTH);
        atualizarPreco(cliente);
        carregarProdutosIniciais();
        revalidate();
        repaint();
        setVisible(true);
    }

    private JButton criarBotao(String texto, Color corFundo, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFont(fonte);
        return botao;
    }

    public void atualizarPreco(Cliente cliente) throws IOException {
        String caminho = "Pedidos_" + cliente.getNome() + ".CSV";
        
        try {
            ArquivoPedidos arquivoPedidos = new ArquivoPedidos();
            arquivoPedidos.carregarCarrinhoArquivo(caminho);

            double total = arquivoPedidos.calcularTotalPedidos(caminho);
                        
            lblTotal.setText("Total: R$ " + String.format("%.2f", total)); 
            revalidate(); 
            repaint();
            carregarProdutosIniciais();
        } catch (IOException e) {

        }
    }

    public void carregarProdutosIniciais() throws IOException {
        String caminho = "Pedidos_" + cliente.getNome() + ".CSV";
        List<String> produtos = ArquivoProdutos.lerArquivo(caminho);

        modelCarrinho.setRowCount(0);
        for (String produto : produtos) {

            String[] dadosProduto = produto.split(","); 
            modelCarrinho.addRow(dadosProduto);
        }
    }

    private void finalizarCompra() {
            TelaPagamento telaPagamento = new TelaPagamento(cliente, pedido, produto, telaPrincipal);
            telaPagamento.setVisible(true);

            dispose();
        }

    private void voltar() {
        dispose();
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        GestaoPedidos gestaoPedidos = new GestaoPedidos();
        Pedido pedidoT = new Pedido();
        Produto produto = new Produto();
        TelaPrincipal telaPrincipal = new TelaPrincipal(cliente, gestaoPedidos, null , pedidoT, produto);
        new TelaConsultarCarrinho(cliente, gestaoPedidos, pedidoT, produto, telaPrincipal).setVisible(true);
    }
}


