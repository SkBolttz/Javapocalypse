package WProjetoPoo;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class GestorReceitas extends Financas {

    private List<Financas> rReceitas = new ArrayList<>();
    private CategoriaReceitas categoriaReceita;
    private LocalDate localDate;

    public List<Financas> getReceitas() {
        return rReceitas;
    }

    public CategoriaReceitas getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(CategoriaReceitas r) {
        try {
            if (r != null) {
                this.categoriaReceita = r;
            } else {
                throw new ExcecaoNula("Nao pode ser nulo!");
            }
        } catch (ExcecaoNula e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getDataReceita() {
        return localDate;
    }

    public void setDataReceita(LocalDate l) {
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
     * Adiciona uma receita à lista de receitas.
     * 
     * Verifica se a receita fornecida não é nula antes de adicioná-la
     * á lista. Caso a receita seja nula, imprime uma mensagem de erro.
     * 
     * @param receita a receita a ser adicionada
     */
    public void incluirReceitas(GestorReceitas receita) {
        if(receita != null){
        rReceitas.add(receita);
        }else{
            System.out.println("Erro ao incluir receita. (NULA)");
        }
    }

    /**
     * Imprime todas as receitas registradas.
     * 
     * Se a lista de receitas estiver vazia, imprime a mensagem "Nenhuma receita
     * listada.".
     */
    public void listarReceitas() {
        if (!rReceitas.isEmpty()) {
            for (Financas receita : rReceitas) {
                System.out.println(receita.toString());
            }
        } else {
            System.out.println("Nenhuma receita listada:");
        }
    }

    /**
     * Retorna uma string que descreve a receita.
     * 
     * A string inclui o nome da receita, a descrição, o valor, a categoria e a
     * data de cadastro. Além disso, se houver receitas relacionadas,
     * ser o incluído na string.
     * 
     * @return uma string que descreve a receita.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome da Receita: ").append(getNome()).append("\n");
        sb.append("Descrição: ").append(getDescricao()).append("\n");
        sb.append("Valor: ").append(getValor()).append("\n");
        sb.append("Categoria: ").append(getCategoriaReceita()).append("\n");
        sb.append("Data: ").append(getDataReceita()).append("\n");

        if (rReceitas.isEmpty()) {
            for (Financas receita : rReceitas) {
                sb.append(receita.toString()).append("\n");
            }
        } else {
            System.out.println("Nenhuma receita listada!");
        }
        return sb.toString();
    }
}
