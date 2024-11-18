package WProjetoPoo;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class GestorDespesas extends Financas {

    private List<Financas> lDespesas = new ArrayList<>();
    private CategoriaDespesas categoriaDespesa;
    private LocalDate localDate;

    public List<Financas> getDespesas() {
        return lDespesas;
    }

    public CategoriaDespesas getCategoriaDespesas() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesas(CategoriaDespesas c) {
        try {
            if (c != null) {
                this.categoriaDespesa = c;
            } else {
                throw new ExcecaoNula("Nao pode ser nulo!");
            }
        } catch (ExcecaoNula e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getLocalDateDespesa() {
        return localDate;
    }

    public void setLocalDateDespesas(LocalDate l) {
        try {
            if (l != null) {
                this.localDate = l;
            } else {
                throw new ExcecaoNula("Nao pode ser nulo!");
            }
        } catch (ExcecaoNula e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Adiciona uma despesa à lista de despesas.
     * 
     * Verifica se a despesa fornecida não é nula antes de adicioná-la à lista.
     * Caso a despesa seja nula, imprime uma mensagem de erro.
     *
     * @param despesa a despesa a ser adicionada
     */
    public void incluirDespesas(GestorDespesas despesa) {
        if (despesa != null) {
            lDespesas.add(despesa);
        }else{
            System.out.println("Erro ao incluir despesa. (NULA)");
        }
    }

    /**
     * Imprime todas as despesas registradas.
     * 
     * Se a lista de despesas estiver vazia, imprime a mensagem "Nenhuma despesa
     * listada.".
     */
    public void listarDespesas() {
        if (!lDespesas.isEmpty()) {
            for (Financas despesa : lDespesas) {
                System.out.println(despesa.toString());
            }
        } else {
            System.out.println("Nenhuma despesa listada.");
        }
    }

    /**
     * Retorna uma string que descreve a despesa.
     * 
     * A string inclui o nome da despesa, a descrição, o valor, a categoria e a
     * data de cadastro. Além disso, se houver despesas relacionadas,
     * ser o incluído na string.
     * 
     * @return uma string que descreve a despesa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome da Despesa: ").append(getNome()).append("\n");
        sb.append("Descrição: ").append(getDescricao()).append("\n");
        sb.append("Valor: ").append(getValor()).append("\n");
        sb.append("Categoria: ").append(getCategoriaDespesas()).append("\n");
        sb.append("Data: ").append(getLocalDateDespesa()).append("\n");

        if (!lDespesas.isEmpty()) {
            for (Financas despesa : lDespesas) {
                sb.append(despesa.toString()).append("\n");
            }
        } else {
            sb.append("Nenhuma despesa registrada.");
        }

        return sb.toString();
    }
}
