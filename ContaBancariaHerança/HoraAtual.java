package Local;

import java.time.LocalTime;

public class HoraAtual {

    private LocalTime now() {
        return LocalTime.now();
    }

    private LocalTime of(int minuto, int segundo, int hora) {
        return LocalTime.of(segundo, minuto, hora);
    }

    public int getHour() {
        return LocalTime.now().getHour();
    }

    public int getMinutes() {
        return LocalTime.now().getHour();
    }

    public int getseconds() {
        return LocalTime.now().getSecond();
    }
}
