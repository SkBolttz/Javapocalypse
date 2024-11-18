package WProjetoPoo;

/*  
 * Criação de uma exceção personalizada para tratamento de entradas nulas.
 */
public class ExcecaoNula extends Exception{
    
    public ExcecaoNula(){
        super("Não pode ser nulo!");
    }

    public ExcecaoNula(String msg){
        super(msg);
    }
    
    public ExcecaoNula(String msg, Throwable cause){
        super(msg, cause);
    }

}
