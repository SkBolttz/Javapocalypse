import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class TelaLogin extends JFrame {

    private Cliente cliente;
    @SuppressWarnings("unused")
    private Pedido pedido;
    @SuppressWarnings("unused")
    private Produto produto;
    public TelaLogin() {

        CadastroClientes cadastroClientes = new CadastroClientes();
        Pedido pedido = new Pedido();
        Produto produto = new Produto();
        
        setTitle("Login e Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(244, 244, 244)); 

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        imageLabel.setIcon(new ImageIcon());
        topPanel.add(imageLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        Color secondaryColor = new Color(255, 255, 255); 
        Color buttonColor = new Color(0, 86, 179); 

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(secondaryColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginLabel = new JLabel("Nome:");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(loginLabel, gbc);

        JTextField loginNomeField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(loginNomeField, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(senhaLabel, gbc);

        JPasswordField loginSenhaField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(loginSenhaField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cliente = new Cliente();

                if (loginNomeField.getText().isEmpty() || new String(loginSenhaField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Por favor, preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                cliente.setNome(loginNomeField.getText());
                cliente.setSenha(new String(loginSenhaField.getPassword()));

                try {
                    ArquivoLogins leituraArquivo = new ArquivoLogins();

                    if(cliente.getNome().equals("1") && cliente.getSenha().equals("1")) {
                        JOptionPane.showMessageDialog(TelaLogin.this,
                                "Seja bem vindo, " + cliente.getNome() + "!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        TelaAdmin telaAdmin = new TelaAdmin();
                        telaAdmin.setVisible(true);

                        TelaLogin.this.dispose();
                    }

                    if(leituraArquivo.leituraLogins(cliente.getNome(), cliente.getSenha(), "AdmLogins.CSV")){
                        JOptionPane.showMessageDialog(TelaLogin.this,
                                "Seja bem vindo, " + cliente.getNome() + "!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                                pedido.setDataDoPedido(LocalDate.now());
                                TelaPrincipal telaPrincipal = new TelaPrincipal(cliente, null, null, pedido, produto);
                                telaPrincipal.setVisible(true);

                        TelaLogin.this.dispose();
                    }else {
                        JOptionPane.showMessageDialog(TelaLogin.this,
                                "Login ou senha incorretos!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                loginNomeField.setText("");
                loginSenhaField.setText("");
            }
        });

        JPanel registroPanel = new JPanel(new GridBagLayout());
        registroPanel.setBackground(secondaryColor);

        JLabel registroNomeLabel = new JLabel("Nome:");
        registroNomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        registroPanel.add(registroNomeLabel, gbc);

        JTextField registroNomeField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        registroPanel.add(registroNomeField, gbc);

        JLabel registroSenhaLabel = new JLabel("Senha:");
        registroSenhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        registroPanel.add(registroSenhaLabel, gbc);

        JPasswordField registroSenhaField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        registroPanel.add(registroSenhaField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        registroPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        registroPanel.add(emailField, gbc);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        registroPanel.add(enderecoLabel, gbc);

        JTextField enderecoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        registroPanel.add(enderecoField, gbc);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBackground(buttonColor);
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registroPanel.add(registrarButton, gbc);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setNome(registroNomeField.getText());
                cliente.setSenha(new String(registroSenhaField.getPassword()));
                cliente.setEmail(emailField.getText());
                cliente.setEndereco(enderecoField.getText());

                if (cliente.getNome().isEmpty() || cliente.getSenha().isEmpty() || cliente.getEmail().isEmpty()
                        || cliente.getEndereco().isEmpty()) {
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Por favor, preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cadastroClientes.existeNome(cliente.getNome())) { 
                    registroNomeField.setText("");
                    registroSenhaField.setText("");
                    emailField.setText("");
                    enderecoField.setText("");
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Nome de usuário já existente!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cadastroClientes.existeEmail(cliente.getEmail())) {
                    registroNomeField.setText("");
                    registroSenhaField.setText("");
                    emailField.setText("");
                    enderecoField.setText("");
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Email já existente!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(registroSenhaField.getPassword() == null){
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Por favor, preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(registroSenhaField.getPassword().length < 8){
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "A senha deve ter pelo menos 8 caracteres!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(emailField.getText().contains("@") == false){
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Por favor, insira um email valido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(TelaLogin.this,
                        "Cadastro realizado com sucesso!",
                        "Cadastro",
                        JOptionPane.INFORMATION_MESSAGE);

                ArquivoLogins arquivo = new ArquivoLogins();
                arquivo.escritaLogins(cliente.getNome(), cliente.getSenha(), "AdmLogins.CSV");

                registroNomeField.setText("");
                registroSenhaField.setText("");
                emailField.setText("");
                enderecoField.setText("");

                cadastroClientes.incluirCliente(cliente);

            }
        });

        tabbedPane.add("Login", loginPanel);
        tabbedPane.add("Registro", registroPanel);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
