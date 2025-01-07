import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAdmin extends JFrame {

    protected JTable tableCarrinho;
    protected DefaultTableModel modelCarrinho;

    public TelaAdmin() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Painel do Administrador");
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

        JLabel lblTitulo = new JLabel("Tabela de Itens Cadastrados", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelTop.add(lblTitulo);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(backgroundColor);

        String[] columnNames = {"ID", "Produto", "Quantidade", "Preço Unitário" , "Quantidade"};
        modelCarrinho = new DefaultTableModel(columnNames, 0);
        tableCarrinho = new JTable(modelCarrinho);
        tableCarrinho.setFont(new Font("Serif", Font.PLAIN, 17));
        tableCarrinho.setBackground(Color.WHITE); 
        tableCarrinho.setForeground(Color.BLACK); 
        tableCarrinho.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tableCarrinho);
        panelCenter.add(scrollPane, BorderLayout.CENTER);

        add(panelCenter, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBottom.setBackground(backgroundColor);

        JPanel panelCarrinho = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelCarrinho.setBackground(backgroundColor);
        panelCarrinho.setBorder(BorderFactory.createTitledBorder("Gerenciamento de Produtos"));

        JButton btnIncluirProduto = criarBotao("Incluir Produto", buttonColor, buttonFont);
        JButton btnRemoverProduto = criarBotao("Remover Produto", buttonColor, buttonFont);
        JButton btnAtualizarProduto = criarBotao("Atualizar Produto", buttonColor, buttonFont);
        JButton btnConsultarProduto = criarBotao("Consultar Produto", buttonColor, buttonFont);

        panelCarrinho.add(btnIncluirProduto);
        panelCarrinho.add(btnRemoverProduto);
        panelCarrinho.add(btnAtualizarProduto);
        panelCarrinho.add(btnConsultarProduto);

        JPanel panelCompras = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelCompras.setBackground(backgroundColor);
        panelCompras.setBorder(BorderFactory.createTitledBorder("Ações Gerais"));

        JButton btnDeslogar = criarBotao("Deslogar", secondaryColor, buttonFont);

        panelCompras.add(btnDeslogar);

        panelBottom.add(panelCarrinho);
        panelBottom.add(panelCompras);

        add(panelBottom, BorderLayout.SOUTH);

        btnIncluirProduto.addActionListener(e -> incluirProduto());
        btnRemoverProduto.addActionListener(e -> removerProduto());
        btnAtualizarProduto.addActionListener(e -> atualizarProduto());
        btnConsultarProduto.addActionListener(e -> consultarProduto());
        btnDeslogar.addActionListener(e -> deslogar());

        carregarProdutosIniciais();

        setVisible(true);
    }

    private JButton criarBotao(String texto, Color corFundo, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFont(fonte);
        return botao;
    }

    public void carregarProdutosIniciais() {
        String caminho = "Lista Produtos.CSV";
        List<String> produtos = ArquivoProdutos.lerArquivo(caminho);

        modelCarrinho.setRowCount(0);
        for (String produto : produtos) {
            String[] dadosProduto = produto.split(",");
            modelCarrinho.addRow(dadosProduto);
        }
    }

    public void atualizarTabela(List<String> produtos) {
        modelCarrinho.setRowCount(0);
        for (String produto : produtos) {
            String[] dadosProduto = produto.split(",");
            modelCarrinho.addRow(dadosProduto);
        }
    }

    private void incluirProduto() {
        TelaInclusaoProduto telaInclusaoProduto = new TelaInclusaoProduto(this);
        telaInclusaoProduto.setVisible(true);
    }

    private void removerProduto() {
        TelaRemoverProduto telaRemoverProduto = new TelaRemoverProduto(this);
        telaRemoverProduto.setVisible(true);
    }

    private void atualizarProduto() {
        TelaBuscaProdutoAtualizar telaBuscaProduto = new TelaBuscaProdutoAtualizar(this);
        telaBuscaProduto.setVisible(true);
    }

    private void consultarProduto() {
        TelaConsultaProduto telaConsultaProduto = new TelaConsultaProduto(null);
        telaConsultaProduto.setVisible(true);
    }

    private void deslogar() {
        this.dispose();
        JOptionPane.showMessageDialog(this, "Deslogado com sucesso.");
        new TelaLogin().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaAdmin().setVisible(true);
    }
}
