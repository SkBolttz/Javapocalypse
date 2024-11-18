package WProjetoPoo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorFinancas {

    private List<Financas> gDespesas = new ArrayList<>();
    private List<Financas> gReceitas = new ArrayList<>();
    GestorDespesas gestorDespesas = new GestorDespesas();
    GestorReceitas gestorReceitas = new GestorReceitas();

    public List<Financas> getDespesas() {
        return gDespesas;
    }

    public void setDespesas(List<Financas> despesas) {
        this.gDespesas = despesas;
    }

    public List<Financas> getReceitas() {
        return gReceitas;
    }

    public void setReceitas(List<Financas> receitas) {
        this.gReceitas = receitas;
    }

    /**
     * Calcula o saldo total das receitas e despesas,
     * considerando todas as receitas e despesas cadastradas.
     * 
     * @return valor do saldo total.
     */
    public String consultarTodoSaldo() {
        double saldoReceita = 0;
        double saldoDespesa = 0;
        if (gDespesas != null) {
            for (Financas despesa : gDespesas) {
                if (despesa != null) {
                    saldoDespesa += despesa.getValor();
                }
            }
        }
        if (gReceitas != null) {
            for (Financas receita : gReceitas) {
                if (receita != null) {
                    saldoReceita += receita.getValor();
                }
            }
        }
        return "Saldo atual: R$" + String.format("%.2f", saldoReceita - saldoDespesa);
    }

    /**
     * Retorna uma string detalhando o saldo atual, incluindo os valores totais
     * de receitas e despesas e o saldo final.
     * 
     * O método percorre todas as receitas e despesas cadastradas, calculando
     * o total de cada um e, em seguida, calcula o saldo atual como a diferença
     * entre o total de receitas e o total de despesas.
     * 
     * @return uma string que descreve o valor total das receitas, o valor total
     *         das despesas e o saldo atual, formatados como valores monetários.
     */

    /**
     * Calcula o saldo total das receitas e despesas,
     * considerando todas as receitas e despesas que tenham data de
     * cadastro anterior a hoje.
     * 
     * @return uma string que descreve o valor total das receitas, o valor total
     *         das despesas e o saldo atual, formatados como valores monet rios.
     */
    public String calcularSaldoAtePeriodoAtual() {
        double saldoReceita = 0;
        double saldoDespesa = 0;
        LocalDate hoje = LocalDate.now();

        for (Financas despesa : gDespesas) {
            if (despesa instanceof GestorDespesas gd && gd.getLocalDateDespesa() != null
                    && !gd.getLocalDateDespesa().isAfter(hoje)) {
                saldoDespesa += despesa.getValor();
            }
        }

        for (Financas receita : gReceitas) {
            if (receita instanceof GestorReceitas gr && gr.getDataReceita() != null
                    && !gr.getDataReceita().isAfter(hoje)) {
                saldoReceita += receita.getValor();
            }
        }
        return "Saldo atual: R$" + String.format("%.2f", saldoReceita - saldoDespesa);
    }

    /**
     * Retorna uma string detalhando o saldo atual, incluindo os valores totais
     * de receitas e despesas e o saldo final.
     * 
     * O método percorre todas as receitas e despesas cadastradas, calculando
     * o total de cada um e, em seguida, calcula o saldo atual como a diferença
     * entre o total de receitas e o total de despesas.
     * 
     * @return uma string que descreve o valor total das receitas, o valor total
     *         das despesas e o saldo atual, formatados como valores monet rios.
     */
    public String consultarExtratoDetalhado() {
        double saldoReceita = 0;
        double saldoDespesa = 0;
        double saldoTotal = 0;

        // Ordenar as despesas e receitas por data
        List<Financas> todosLancamentos = new ArrayList<>();
        todosLancamentos.addAll(gDespesas);
        todosLancamentos.addAll(gReceitas);

        // Ordenar os lançamentos por data (do mais antigo para o mais recente)
        todosLancamentos.sort((f1, f2) -> {
            if (f1 instanceof GestorDespesas && f2 instanceof GestorDespesas) {
                return ((GestorDespesas) f1).getLocalDateDespesa()
                        .compareTo(((GestorDespesas) f2).getLocalDateDespesa());
            } else if (f1 instanceof GestorReceitas && f2 instanceof GestorReceitas) {
                return ((GestorReceitas) f1).getDataReceita().compareTo(((GestorReceitas) f2).getDataReceita());
            }
            return 0;
        });

        // Construção do extrato detalhado
        StringBuilder extrato = new StringBuilder();

        // Processar cada lançamento
        for (Financas lancamento : todosLancamentos) {
            if (lancamento instanceof GestorDespesas) {
                GestorDespesas despesa = (GestorDespesas) lancamento;
                saldoDespesa += despesa.getValor();
                extrato.append("Despesa: ").append(despesa.getDescricao())
                        .append(" | Valor: R$").append(String.format("%.2f", despesa.getValor()))
                        .append(" | Data: ").append(despesa.getLocalDateDespesa())
                        .append("\n");
            } else if (lancamento instanceof GestorReceitas) {
                GestorReceitas receita = (GestorReceitas) lancamento;
                saldoReceita += receita.getValor();
                extrato.append("Receita: ").append(receita.getDescricao())
                        .append(" | Valor: R$").append(String.format("%.2f", receita.getValor()))
                        .append(" | Data: ").append(receita.getDataReceita())
                        .append("\n");
            }

            saldoTotal = saldoReceita - saldoDespesa;

            // Mostrar o saldo após cada lançamento
            extrato.append("Saldo após lançamento: R$")
                    .append(String.format("%.2f", saldoTotal)).append("\n\n");
        }

        // Retorna o extrato detalhado
        return extrato.toString();
    }
}
