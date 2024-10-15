package heranc2;

import Enum.TipoMovimento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Movimento {

    private LocalDate data;
    private LocalTime hora;
    private double valor;
    private TipoMovimento tipoMovimento;

    public Movimento(double valor, TipoMovimento tipo) {
        this.valor = valor;
        this.tipoMovimento = tipo;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
    }

    public LocalDate getData() {
        return this.data;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public double getValor() {
        return valor;
    }

    public TipoMovimento getTipoMovimento() {
        return this.tipoMovimento;
    }
    
    public static List<Movimento> getMovimentos(){
        List<Movimento> movimentos = new ArrayList<>();
        movimentos.add(new Movimento(1000.00, TipoMovimento.CREDITO));
        movimentos.add(new Movimento(250.00, TipoMovimento.DEBITO));
        return movimentos;
    }

}
