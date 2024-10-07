package assosiacoes;


import org.junit.Test;
import static org.junit.Assert.*;


public class ImovelTest {
    
       
     /**
     * Caso de teste 1
     */
    @Test(expected=IllegalArgumentException.class)
    public void test01_IdentificarImovel() throws Exception{
        Imovel im = new Imovel();
        im.setArea(-5);
    }
    
    /**
     * Caso de teste 2
     */
    @Test(expected=IllegalArgumentException.class)
    public void test02_IdentificarImovel() throws Exception{
        Imovel im = new Imovel();
        Bairro br = new Bairro();
        im.setFinalidade(Finalidade.RESIDENCIAL);
        br.setCoeficienteIptu(1);
        
        im.calcularIptu();
    }
    
     /**
     * Caso de teste 3
     */
     @Test(expected=IllegalArgumentException.class)
     public void test03_IdentificarImovel() throws Exception{
         Imovel im = new Imovel();
         Bairro br = new Bairro();
         br.setCoeficienteIptu(1);
         im.setArea(250);
         
         im.calcularIptu();
     }
     
     /**
     * Caso de teste 4
     */
     @Test(expected=IllegalArgumentException.class)
     public void test04_IdentificarImovel() throws Exception{
         Imovel im = new Imovel();
         im.setFinalidade(Finalidade.RESIDENCIAL);
         im.setArea(400);
         
         im.calcularIptu();
     }
     
      /**
     * Caso de teste 5
     */
     @Test
     public void test05_IdentificarImovel(){
         Imovel im = new Imovel();
         Bairro br = new Bairro();
         br.setNome("Centro");
         im.setBairro(br);
         br.setCoeficienteIptu(1);
         im.setArea(400);
         im.setFinalidade(Finalidade.RESIDENCIAL);
         
         assertEquals(400, im.calcularIptu(), 0.00);
     }
     
     /**
     * Caso de teste 6
     */
     @Test
     public void test06_IdentificarImovel(){
      Imovel im = new Imovel();
      Bairro br = new Bairro();
      br.setNome("Centro");
      im.setBairro(br);
      br.setCoeficienteIptu(1);
      im.setArea(80);
      im.setFinalidade(Finalidade.COMERCIAL);
      
      assertEquals(500, im.calcularIptu(), 0.00);
     }
     
     /**
     * Caso de teste 7
     */
     @Test
     public void test07_IdentificarImovel(){
      Imovel im = new Imovel();
      Bairro br = new Bairro();
      br.setNome("Centro");
      im.setBairro(br);
      br.setCoeficienteIptu(1);
      im.setArea(250);
      im.setFinalidade(Finalidade.COMERCIAL);
      
      assertEquals(1000, im.calcularIptu(), 0.00);
     }
     
     /**
     * Caso de teste 8
     */
     @Test
     public void test08_IdentificarImovel(){
      Imovel im = new Imovel();
      Bairro br = new Bairro();
      br.setNome("Centro");
      im.setBairro(br);
      br.setCoeficienteIptu(1);
      im.setArea(500);
      im.setFinalidade(Finalidade.COMERCIAL);
      
      assertEquals(1275, im.calcularIptu(), 0.00);
     }
     
     /**
     * Caso de teste 9
     */
     @Test
     public void test09_IdentificarImovel(){
         Imovel im = new Imovel();
         Bairro br = new Bairro();
         br.setNome("Centro");
         im.setBairro(br);
         br.setCoeficienteIptu(1);
         im.setArea(2000);
         im.setFinalidade(Finalidade.INDUSTRIAL);
         
         assertEquals(1000, im.calcularIptu(), 0.00);
     }
     
     /**
     * Caso de teste 10
     */
     @Test
     public void test10_IdentificarImovel(){
         Imovel im = new Imovel();
         Bairro br = new Bairro();
         br.setNome("Centro");
         im.setBairro(br);
         br.setCoeficienteIptu(1);
         im.setArea(3000);
         im.setFinalidade(Finalidade.INDUSTRIAL);
         
         assertEquals(1650, im.calcularIptu(), 0.01);
     }
     
     /**
     * Caso de teste 11
     */
     @Test
     public void test11_IdentificarImovel(){
         Imovel im = new Imovel();
         Bairro br = new Bairro();
         br.setNome("Centro");
         im.setBairro(br);
         br.setCoeficienteIptu(2.5);
         im.setArea(500);
         im.setFinalidade(Finalidade.RESIDENCIAL);
         
         assertEquals(1250, im.calcularIptu(), 0.00);
     }
}