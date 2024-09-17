package impostorendaprova;
public class Interface extends javax.swing.JFrame {

    public Interface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfSalario = new javax.swing.JTextField();
        btAvaliar = new javax.swing.JButton();
        tfFaixa = new javax.swing.JTextField();
        tfImposto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Salário:");

        btAvaliar.setText("Avaliar");
        btAvaliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAvaliarActionPerformed(evt);
            }
        });

        tfFaixa.setEnabled(false);

        tfImposto.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btAvaliar)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tfImposto, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(tfFaixa))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btAvaliar)
                .addGap(18, 18, 18)
                .addComponent(tfFaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfImposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btAvaliarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        ImpostoRendaProva funci = new ImpostoRendaProva(null, 0);
        
        funci.setSalario((Double.valueOf(tfSalario.getText())));
        double imposto = funci.calcularIrpf();
        
        double salario = Double.parseDouble(tfSalario.getText());
        
        if(salario >= 1903.99){
        tfImposto.setText(String.format("O imposto a pagar é de R$%.2f\n", imposto));
        }else{
        tfImposto.setText(String.format("Funcionário Isento"));
        }
        FaixaIrpf faixa = funci.identificarFaixaIrpf();
        
        if(faixa.equals(FaixaIrpf.PRIMEIRA)){
            tfFaixa.setText(String.format("O salário está na primeira faixa"));
        }else if(faixa.equals(FaixaIrpf.SEGUNDA)){
            tfFaixa.setText(String.format("O salário está na segunda faixa"));
        }else if(faixa.equals(FaixaIrpf.TERCEIRA)){
        tfFaixa.setText(String.format("O salário está na terceira faixa"));
        }else if(faixa.equals(FaixaIrpf.QUARTA)){
            tfFaixa.setText(String.format("O salário está na quarta faixa"));
        }else if(faixa.equals(FaixaIrpf.QUINTA)){
            tfFaixa.setText(String.format("O salário está na quinta faixa"));
        }
    }                                         

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btAvaliar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tfFaixa;
    private javax.swing.JTextField tfImposto;
    private javax.swing.JTextField tfSalario;
    // End of variables declaration                   

}
