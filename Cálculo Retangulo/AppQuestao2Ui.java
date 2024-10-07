 package com.mycompany.retanguloexercicio;

import javax.swing.JOptionPane;

public class AppQuestao2Ui {

    public static void main(String[] args) {
        
        Retangulo r1 = new Retangulo();        
        
        r1.setAltura(Integer.parseInt(JOptionPane.showInputDialog("Informe a altura:")));
        r1.setComprimento(Integer.parseInt(JOptionPane.showInputDialog("Informe o comprimento:")));
        
        double resultado = r1.calcularArea();
        double resultado2 = r1.calcularPerimetro();
        
        JOptionPane.showMessageDialog(null, "resultado da área: " + resultado);
        JOptionPane.showMessageDialog(null, "resultado do perímetro: " + resultado2);
    }
}
