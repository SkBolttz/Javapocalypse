
package com.mycompany.retanguloexercicio;
public class Retangulo {

    private int altura;
    private int comprimento;
    
    public Retangulo() {
    }

    public Retangulo(int altura, int comprimento) {
        this.altura = altura;
        this.comprimento = comprimento;
    }

    public void setAltura(int altura) {
        if(altura <= 0){
          throw new IllegalArgumentException ("Valor incorreto para altura: " + altura);
        }else{
        this.altura = altura;
    }
    }

    public void setComprimento(int comprimento) {
        if(comprimento <= 0){
          throw new IllegalArgumentException ("Valor incorreto para altura: " + comprimento);
        }else{
        this.comprimento = comprimento;
    }
    }

    public int getAltura() {
        return altura;
    }

    public int getComprimento() {
        return comprimento;
    }
    
    public int calcularPerimetro(){
        return 2 * (altura + comprimento);
    }
    
    public int calcularArea(){
        return altura * comprimento;
    }
}
