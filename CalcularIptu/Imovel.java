package assosiacoes;
public class Imovel {

    private String endereco;
    private int area;
    private Bairro bairro;
    private Finalidade finalidade;

    /**
     * Retorna o endereço do imóvel.
     *
     * @return endereço do imóvel.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Altera o endereço do imóvel.
     *
     * @param endereco novo endereço do imóvel.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna a área do imóvel.
     *
     * @return a área do imóvel.
     */
    public int getArea() {
        return area;
    }

    /**
     * Altera a área do imóvel.
     *
     * @param area nova área do imóvel.
     * @throws IllegalArgumentException se o valor da área for negativo
     */
    public void setArea(int area) {
        if(area < 1){
            throw new IllegalArgumentException("Valor da área não pode ser negativa!");
        }else{
        this.area = area;
        }
    }

    /**
     * Retorna o bairro do imóvel.
     *
     * @return o bairro do imóvel.
     */
    public Bairro getBairro() {
        return bairro;
    }


    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    /**
     * Retorna a finalidade do imóvel.
     *
     * @return a finalidade do imóvel.
     */
    public Finalidade getFinalidade(){
        return finalidade;
    }
    
    /**
     * Altera a finalidade do imóvel.
     * 
     * @param finalidade nova finalidade do imóvel.
     */
    public void setFinalidade(Finalidade finalidade){
        this.finalidade = finalidade;
    }
    
    /**
     * Calcula o valor do IPTU do imóvel com base em seu bairro e finalidade.
     * 
     * @return o valor do IPTU do imóvel.
     * @throws IllegalArgumentException se o bairro ou finalidade do imóvel não estiverem definidos.
     */
    public double calcularIptu(){         
        double valorIptu = 0;
        
        
        if(bairro == null){
            throw new IllegalArgumentException("Bairro não definido!");
        }
        else if(bairro.getCoeficienteIptu() < 1){
            throw new IllegalArgumentException("Coeficiente não pode ser negativo!");
        }else if(finalidade == null){
            throw new IllegalArgumentException("Finalidade não definida!");
        }else{
                switch(finalidade){
                    case Finalidade.RESIDENCIAL:
                        if(bairro.getCoeficienteIptu() > 1){
                            valorIptu = (area * 1) * bairro.getCoeficienteIptu();
                            System.out.println("Bairro Nobre!");
                        }else if(bairro.getCoeficienteIptu() <= 1){
                            valorIptu = (area * 1) * bairro.getCoeficienteIptu();
                            System.out.println("Bairro Menos populosos / Afastados!");
                        }
                        break;
                        
                    case Finalidade.COMERCIAL:
                        if(bairro.getCoeficienteIptu() > 1){
                            if(area <= 100){
                                valorIptu = 500 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Nobre!");
                            }else if(area > 100 && area <= 400){
                                valorIptu = 1000 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Nobre!");
                            }else if(area > 400){
                                valorIptu = 2.55 * area;
                                System.out.println("Bairro Nobre!");
                            }
                        }else if(bairro.getCoeficienteIptu() <= 1){
                            if(area <= 100){
                                valorIptu = 500 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Menos populosos / Afastados!");
                            }else if(area > 100 && area <= 400){
                                valorIptu = 1000 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Menos populosos / Afastados!");
                            }else if(area > 400){
                                valorIptu = 2.55 * area;
                                System.out.println("Bairro Menos populosos / Afastados!");
                            }
                        }
                        break;
                        
                    case Finalidade.INDUSTRIAL:
                        if(bairro.getCoeficienteIptu() > 1){
                            if(area <= 2000){
                                valorIptu = 1000 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Nobre!");
                            }else if(area > 2000){
                                valorIptu = 0.55 * area;
                                System.out.println("Bairro Nobre!");
                            }
                        }else if(bairro.getCoeficienteIptu() <= 1){
                             if(area <= 2000){
                                valorIptu = 1000 * bairro.getCoeficienteIptu();
                                System.out.println("Bairro Menos populosos / Afastados!");
                            }else if(area > 2000){
                                valorIptu = 0.55 * area;
                                System.out.println("Bairro Menos populosos / Afastados!");
                            }
                        }
                        break;
                        
                    default:
                        System.out.println("Opção inválida!");
                }
        }
        return valorIptu;
    }
}
