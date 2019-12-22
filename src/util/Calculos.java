package util;

import java.util.Date;

/**
 * @author MDT
 */
public class Calculos {
    
    
    Formatar formatar = new Formatar();
    private Double montante = 0.0, ValorTotalProduto = 0.0;
    private Long QuantidadeTotal = 0l;
    
    //Calculo de Juros Compostos já parcelado
    public Double jurosCompostosParcelado(Double capital, Double taxajuros, Integer tempo){
        montante = capital * Math.pow((1 + (taxajuros/100)), tempo);
        return montante / tempo;
    }
    
    //Calculo de Juros Simples já parcelado
    public Double jurosSimplesParcelado(Double capital, Double taxajuros, Integer tempo){
        montante = capital * (1 + ((taxajuros/100) * tempo));
        return montante / tempo;
    }
    
    //Calculo de valor do Produto se os valores forem fixos e somados
    public Double calculaValorProduto(Double ValorCompra, Double ComissaoVenda, Double Lucro, Double CustoTransporte){
        ValorTotalProduto = ValorCompra + (ComissaoVenda) + (Lucro) + CustoTransporte;
        return ValorTotalProduto;
    }
    
    //Calculo de valor dos produtos se os valores forem em porcentagem
    public Double calculaValorProdutoPorcentagem(Double ValorCompra, Double ComissaoVenda, Double Lucro, Double CustoTransporte){
        ValorTotalProduto = (ValorCompra * (ComissaoVenda / 100)) + (ValorCompra * (Lucro / 100)) + (ValorCompra * (CustoTransporte / 100)) + ValorCompra;
        return ValorTotalProduto;
    }
    
    //Calculo para atualizar a entrada de produtos no estoque dá saida e retorno de valores
    public Long atualizaEntradaValoresEstoque(Long Quantidade1, Long Quantidade2, Long Quantidade3){
        QuantidadeTotal = (Quantidade1 + Quantidade2) - Quantidade3;
        return QuantidadeTotal;
    }
    
    //Calculo para dar entrada de produtos em estoque 
    public Long entradaValoresEstoque(Long Quantidade1, Long Quantidade2){
        QuantidadeTotal = (Quantidade1 + Quantidade2);
        return QuantidadeTotal;
    }
    
    public Double jurosCompostos(Double capital, Double taxajuros, Integer tempo, Date data){
       
//        GregorianCalendar gc = new GregorianCalendar();
//       
//        for (int e = 0; e < tempo; e++) {
//            gc.setTime(data);
//            gc.add(GregorianCalendar.MONTH, e);
//        
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            Date d = gc.getTime();
            
            montante = capital * Math.pow((1 + (taxajuros/100)), tempo);
            
//        }
        return montante / tempo;
    }
}
