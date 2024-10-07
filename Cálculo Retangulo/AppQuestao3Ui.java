package com.mycompany.retanguloexercicio;

import javax.swing.JOptionPane;

public class AppQuestao3Ui {
    
    public static void main(String[] args) {
                
        int altura = Integer.parseInt(JOptionPane.showInputDialog("Informe a Altura"));
        int comprimento = Integer.parseInt(JOptionPane.showInputDialog("Informe o comprimento:"));

        
        Retangulo r2 = new Retangulo(altura, comprimento);

        int resultado1 = r2.calcularArea();
        int resultado2 = r2.calcularPerimetro();
        
        JOptionPane.showMessageDialog(null, "Resultado da Área: " + resultado1);
        JOptionPane.showMessageDialog(null, "Resultado do Perímetro: " + resultado2);
        
    
}
}
