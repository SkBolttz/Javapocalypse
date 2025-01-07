import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JTable tableCarrinho;
    private DefaultTableModel modelCarrinho;
    Cliente cliente;
    @SuppressWarnings("unused")
    private GestaoPedidos gestaoPedido;
    private JLabel lblTotal;
    @SuppressWarnings("unused")
    private TelaConsultarCarrinho telaConsultarCarrinho;
    @SuppressWarnings("unused")
    private Pedido pedido;
    @SuppressWarnings("unused")
    private Produto produto;

    public TelaPrincipal(Cliente cliente, GestaoPedidos gestaoPedido, TelaConsultarCarrinho telaConsultarCarrinho,
            Pedido pedido, Produto produto) throws IOException {

        this.pedido = pedido;
        this.cliente = cliente;
        this.gestaoPedido = gestaoPedido;
        this.telaConsultarCarrinho = telaConsultarCarrinho;
        this.produto = produto;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Painel de Vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cores e fontes
        Color primaryColor = new Color(72, 133, 237); 
        Color secondaryColor = new Color(255, 122, 122); 
        Color buttonColor = new Color(60, 179, 113); 
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color backgroundColor = new Color(245, 245, 245); 

        getContentPane().setBackground(backgroundColor);

        JPanel panelTop = new JPanel();
        panelTop.setBackground(primaryColor);
        panelTop.setPreferredSize(new Dimension(800, 50));
        JLabel lblTitulo = new JLabel("Painel de Vendas", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelTop.add(lblTitulo);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(backgroundColor);

        String[] columnNames = { "ID", "Produto", "Descrição", "Preço Unitário", "Quantidade" };
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

        JButton btnIncluirNoCarrinho = criarBotao("Incluir no Carrinho", buttonColor, buttonFont);
        JButton btnExcluirDoCarrinho = criarBotao("Excluir do Carrinho", buttonColor, buttonFont);
        JButton btnLimparCarrinho = criarBotao("Limpar Carrinho", secondaryColor, buttonFont);

        panelCarrinho.add(btnIncluirNoCarrinho);
        panelCarrinho.add(btnExcluirDoCarrinho);
        panelCarrinho.add(btnLimparCarrinho);

        JPanel panelCompras = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelCompras.setBackground(backgroundColor);
        panelCompras.setBorder(BorderFactory.createTitledBorder("Ações Gerais"));
        JButton btnMostrarCompras = criarBotao("Mostrar Compras", buttonColor, buttonFont);
        JButton btnDeslogar = criarBotao("Deslogar", secondaryColor, buttonFont);

        panelCompras.add(btnMostrarCompras);
        panelCompras.add(btnDeslogar);

        panelBottom.add(panelCarrinho);
        panelBottom.add(panelCompras);

        
        add(panelBottom, BorderLayout.SOUTH);

        btnIncluirNoCarrinho.addActionListener(e -> {
            try {
                incluirNoCarrinho();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnExcluirDoCarrinho.addActionListener(e -> excluirDoCarrinho());
        btnLimparCarrinho.addActionListener(e -> {
            new TelaConfirmacao(cliente, this, produto).setVisible(true);
        });

        btnMostrarCompras.addActionListener(e -> {
            try {
                new TelaConsultarCarrinho(cliente, gestaoPedido, pedido, produto, this).setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnDeslogar.addActionListener(e -> deslogar());


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
        String caminho = "Lista Produtos.CSV";
        List<String> produtos = ArquivoProdutos.lerArquivo(caminho);

        modelCarrinho.setRowCount(0); 
        for (String produto : produtos) {

            String[] dadosProduto = produto.split(","); 
            modelCarrinho.addRow(dadosProduto);

        }
    }

    private void incluirNoCarrinho() throws IOException {
        TelaIncluirCarrinho telaIncluirCarrinho = new TelaIncluirCarrinho(cliente, this);
        telaIncluirCarrinho.setVisible(true);
    }

    private void excluirDoCarrinho() {
        TelaBuscaProdutoREMCarrinho telaBuscaProduto = new TelaBuscaProdutoREMCarrinho(cliente, this);
        telaBuscaProduto.setVisible(true);
    }

    private void deslogar() {
        this.dispose();
        JOptionPane.showMessageDialog(this, "Deslogado com sucesso.");
        new TelaLogin().setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        GestaoPedidos gestaoPedidos = new GestaoPedidos();
        Pedido pedido = new Pedido(); 
        Produto produto = new Produto();
        TelaPrincipal telaPrincipal = new TelaPrincipal(cliente, gestaoPedidos, null, pedido, produto);
        TelaConsultarCarrinho telaConsultarCarrinho = new TelaConsultarCarrinho(cliente, gestaoPedidos, pedido, produto, telaPrincipal);
        new TelaPrincipal(cliente, gestaoPedidos, telaConsultarCarrinho, pedido, produto).setVisible(true);
    }
}
