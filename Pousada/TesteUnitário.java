package prova1;
import org.junit.Test;
import static org.junit.Assert.*;

public class EstadiaTest {
  
     /**
     * Caso de teste 1
     */
    @Test
    public void test01_IdentificarDiaria() {
        Estadia cliente = new Estadia(Periodo.ALTA_TEMPORADA, 8);
        assertEquals(1411.20, cliente.precoPagar(), 0.01);
    }
     /**
     * Caso de teste 2
     */
    @Test
    public void test02_IdentificarDiaria() {
        Estadia cliente = new Estadia(Periodo.MEDIA_TEMPORADA, 1);
        assertEquals(157.50, cliente.precoPagar(), 0.00);
    }
     /**
     * Caso de teste 3
     */
    @Test
    public void test03_IdentificarDiaria() {
        Estadia cliente = new Estadia(Periodo.BAIXA_TEMPORADA, 5);
        assertEquals(525.00, cliente.precoPagar(), 0.00);
    }
     /**
     * Caso de teste 4
     */
    @Test
    public void test04_IdentificarDiaria() {
        Estadia cliente = new Estadia(Periodo.BAIXA_TEMPORADA, 1);
        assertEquals(110.25, cliente.precoPagar(), 0.00);
    }
     /**
     * Caso de teste 5
     */
    @Test
    public void test05IdentificarDiaria() {
        Estadia cliente = new Estadia(Periodo.MEDIA_TEMPORADA, 3);
        assertEquals(450.00, cliente.precoPagar(), 0.00);
    }
}
