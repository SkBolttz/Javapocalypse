Classe Estadia

package prova1;
/**
 *
 * @author henrique
 */
public class Estadia {

    private Periodo periodo;
    private int quantidadeDias;

    /**
     * Utilizada classe construtor com valor parametrizados /n
     * @param periodo
     * @param quantidadeDias 
     */
    public Estadia(Periodo periodo, int quantidadeDias) {
        this.periodo = periodo;
        this.quantidadeDias = quantidadeDias;
    }

    /**
     *
     * @return
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Realiza a verficação, caso o período esteja vazio, retorna uma exceção /n
     * @param periodo 
     */
    public void setPeriodo(Periodo periodo) {
        if (periodo == null) {
            throw new IllegalArgumentException("Período não pode ser vazio!");
        } else {
            this.periodo = periodo;
        }
    }

    /**
     *
     * @return
     */
    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    /**
     * Realiza a verificação, caso seja menor que um, aplica uma exceção /n
     * Caso seja alta temporada e o cliente fique menos que cinco dias, aplica uma exceção /n
     * @param quantidadeDias 
     */
    public void setQuantidadeDias(int quantidadeDias) {
        if (quantidadeDias < 1) {
            throw new IllegalArgumentException("Valor não pode ser menor que um!");
        } else if (periodo == Periodo.ALTA_TEMPORADA && quantidadeDias < 5) {
            throw new IllegalArgumentException("Em alta temporada, a quantidade de dias não pode ser menor que 5!");
        } else {
            this.quantidadeDias = quantidadeDias;
        }
    }

    /**
     * Nesta clase é verificado o valor a ser pago pelo cliente /n
     * contabilizando a quantidade de dias e o período que o mesmo ficou no local /n
     * @return 
     */
    public float precoPagar() {
        float valorBase = 150;
        float valorTotal;

        switch (periodo) {
            case BAIXA_TEMPORADA:
                valorTotal = quantidadeDias * valorBase;
                if (quantidadeDias == 1) {
                    valorTotal += valorTotal * 0.05f;
                    valorTotal -= valorTotal * 0.30f;
                } else {
                    valorTotal -= valorTotal * 0.30f;
                }
                break;

            case ALTA_TEMPORADA:
                valorTotal = quantidadeDias * valorBase;
                if (quantidadeDias == 1) {
                    valorTotal += valorTotal * 0.25f;
                } else if (quantidadeDias >= 7) {
                    valorTotal += valorTotal * 0.20f;
                    valorTotal -= valorTotal * 0.02f;
                } else {
                    valorTotal += valorTotal * 0.20f;
                }
                break;

            case MEDIA_TEMPORADA:
                valorTotal = quantidadeDias * valorBase;
                if (quantidadeDias == 1) {
                    valorTotal += valorTotal * 0.05f;
                }else{
                    valorTotal = quantidadeDias * valorBase;
                }
                break;

            default:
                throw new IllegalArgumentException("Período desconhecido!");
        }

        return valorTotal;
    }
}
