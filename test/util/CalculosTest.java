package util;

import java.sql.Date;
import java.time.LocalDate;
import org.junit.Test;

/**
 * @author MDiasT
 */
public class CalculosTest {
    
    Double montante = 0.0, capital = 100.0, taxajuros = 10.0;
    Integer tempo = 24;
    Date dataatual = Date.valueOf(LocalDate.now());
        
    Calculos calculo = new Calculos();
    Formatar format = new Formatar();
    
    public CalculosTest() {
    }

    @Test
    public void testSomar() {
    }

    @Test
    public void testSubtrair() {
    }

    @Test
    public void testDividir() {
    }

    @Test
    public void testMultiplicar() {
    }

    @Test
    public void testMostrarResultado() {
    }

    @Test
    public void testJurosCompostos() {
        
    }

    @Test
    public void testJurosSimples() {
        //montante = calculo.jurosSimplesParcelado(capital, taxajuros, tempo);
    }

    @Test
    public void testJurosCompostosParcelado() {
    }

    @Test
    public void testJurosSimplesParcelado() {
    }

    @Test
    public void testCalculaValorProduto() {
    }

    @Test
    public void testCalculaValorProdutoPorcentagem() {
    }

    @Test
    public void testAtualizaEntradaValoresEstoque() {
    }

    @Test
    public void testEntradaValoresEstoque() {
    }

    @Test
    public void testJurosCompostosDataComEntrada() {
        System.out.println(format.convertendoDoubleparaDoubleBrasil(calculo.jurosCompostos(capital, taxajuros, tempo, dataatual).toString()));
    }

    @Test
    public void testJurosCompostosDataSemEntrada() {
    }
    
}
