package WProjetoPoo;

/*  
 * Criação de uma excecão personalizada para tratamento de entradas negativas.
 */
public class ExcecaoValor extends Exception{
    
    public ExcecaoValor(){
        super("Valor não pode ser negativo!");
    }

    public ExcecaoValor (String msg){
        super(msg);
    }

    public ExcecaoValor(String msg, Throwable cause){
        super(msg, cause);
    }
}
