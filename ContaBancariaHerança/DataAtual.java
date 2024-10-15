package Local;

import java.time.LocalDate;

public class DataAtual {

    public LocalDate now() {
        return LocalDate.now(); // Retorna a data atual
    }

    public int getYear() {
        return LocalDate.now().getDayOfYear();
    }

    public int getMonthValue() {
        return LocalDate.now().getDayOfMonth();
    }

    public int getDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    public LocalDate of(int mes, int dia, int ano) {
        return LocalDate.of(dia, mes, ano);
    }

    public LocalDate plusDay(int dias) {
        return LocalDate.now().plusDays(dias);
    }
}
