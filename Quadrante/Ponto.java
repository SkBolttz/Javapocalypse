package com.mycompany.quadrante;
public class Ponto {
    private int y;
    private int x;

    /**
     * Este construtor parte de valores flexíveis.
     * 
     *  @param y.
     *  @param x.
     * 
     */
    
    public Ponto() {
    }
    
    /**
     * Este construtor parte de valores fixos, pré definidos dentro do construtor.
     * @param y
     * @param x 
     */
    public Ponto(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Este Parâmetro identifica onde esta o quadrante.
     * 
     * y > 0 e y > = Quadrante 1
     * x < 0 e y > 0 = Quadrante 2
     * x < 0 e y < 0 = Quadrante 3
     * x > 0 e y < 0 = Quadrante 4
     * caso ambos sejam 0 = Nenhum
     * @return Irá retornar em qual Quadrante esta localizado.
     */
    public Quadrante identificarQuadrante(){
        if(x > 0 && y > 0){
            return Quadrante.PRIMEIRO;
        }else if(x < 0 && y > 0){
            return Quadrante.SEGUNDO;
        }else if(x < 0 && y < 0){
            return Quadrante.TERCEIRO;
        }else if(x > 0 && y < 0){
            return Quadrante.QUARTO;    
        }else{
            return Quadrante.NENHUM;
        }
    }
    
    /**
     * Neste parâmetro irá verificar se o valor esta sendo indicado dentro de X.
     * @return Caso esteja dentro de X, irá retornar true;
     */
    public boolean estaIndicandoSobreX(){
            if(x > 0 && y > 0 || x < 0 && y > 0){
                return true;
            }
            return false;
    }
    
    /**
     * Neste parâmetro irá verificar se o valor esta sendo indicado dentro Y.
     * @return Caso esteja dentro de Y, irá retornar true;
     */
    public boolean estaIndicandoSobreY(){
            if(x < 0 && y < 0 || x > 0 && y < 0){
                return true;
            }
            return false;
    }
    
    /**
     * Neste Parâmetro irá calcular a distância entre pontos flexíveis.
     * @param outroPonto
     * @return Irá retornar a distância dos pontos;
     */
    public double calcularDistancia(Ponto outroPonto){        
        double X = outroPonto.getX() - this.getX();
        double Y = outroPonto.getY() - this.getY();
        
        double d = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
        return d;
    }
    
    /**
     * Neste Parâmetro irá caluclar a distância entre pontos pré definidos.
     * @param p1
     * @param p2
     * @return Irá retornar a distância dos pontos;
     */
    public double calcularDistancia(Ponto p1, Ponto p2){
        double X = p2.getX() - p1.getX();
        double Y = p2.getY() - p1.getY();
        
        double d = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
        return d;
    }
    
}
