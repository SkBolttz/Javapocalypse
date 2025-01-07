import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class TelaAtualizarProduto extends JFrame {
    @SuppressWarnings("unused")
    private Produto produto;
    @SuppressWarnings("unused")
    private TelaAdmin telaAdmin;
    public TelaAtualizarProduto(Produto produto, TelaAdmin telaAdmin) {

        this.telaAdmin = telaAdmin;
        this.produto = produto;
        CadastroProdutos cadastroProdutos = new CadastroProdutos();

        setTitle("Atualizar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        JLabel lblNome = new JLabel("Novo Nome:");
        lblNome.setBounds(20, 20, 150, 25);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(180, 20, 180, 25);
        add(txtNome);

        JLabel lblDescricao = new JLabel("Nova Descrição:");
        lblDescricao.setBounds(20, 60, 150, 25);
        add(lblDescricao);

        JTextField txtDescricao = new JTextField();
        txtDescricao.setBounds(180, 60, 180, 25);
        add(txtDescricao);

        JLabel lblPreco = new JLabel("Novo Preço:");
        lblPreco.setBounds(20, 100, 150, 25);
        add(lblPreco);

        JTextField txtPreco = new JTextField();
        txtPreco.setBounds(180, 100, 180, 25);
        add(txtPreco);

        JLabel lblQuantidade = new JLabel("Nova Quantidade:");
        lblQuantidade.setBounds(20, 140, 150, 25);
        add(lblQuantidade);

        JTextField txtQuantidade = new JTextField();
        txtQuantidade.setBounds(180, 140, 180, 25);
        add(txtQuantidade);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(80, 200, 100, 30);
        add(btnAtualizar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 200, 100, 30);
        add(btnCancelar);
        
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArquivoProdutos arquivoProdutos = new ArquivoProdutos();

                produto.setNome(txtNome.getText());
                produto.setDescricao(txtDescricao.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

                if (txtNome.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtPreco.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(TelaAtualizarProduto.this,
                            "Por favor, preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    arquivoProdutos.carregarProdutosDoArquivo("Lista Produtos.CSV");
                    arquivoProdutos.atualizarProduto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade(), "Lista Produtos.CSV");
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(TelaAtualizarProduto.this,
                            "Erro ao atualizar o produto: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cadastroProdutos.atualizarProduto(produto);
                JOptionPane.showMessageDialog(TelaAtualizarProduto.this, "Produto atualizado com sucesso!");

                if(telaAdmin != null){
                    @SuppressWarnings("static-access")
                    List<String> produtos = arquivoProdutos.lerArquivo("Lista Produtos.CSV");
                    telaAdmin.atualizarTabela(produtos);
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
