/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PagarReceberClienteParcelamentoDTO;
import DTO.PagarReceberDTO;
import org.junit.Test;
import static org.junit.Assert.*;
import service.PagarReceberService;

/**
 *
 * @author MDiasT
 */
public class PagarReceberDAOTest {
    
    PagarReceberDAO pgDAO = new PagarReceberDAO();
    PagarReceberService prSER = new PagarReceberService();
    PagarReceberClienteParcelamentoDTO prcpDTO = new PagarReceberClienteParcelamentoDTO();
    
//    @Test
//    public void testIncluir() throws Exception {
//
//    }
//
//    @Test
//    public void testAlterar() throws Exception {
//
//    }
//
//    @Test
//    public void testExcluir() throws Exception {
//
//    }
//
//    @Test
//    public void testListarOrdem() throws Exception {
//
//    }
//
//    @Test
//    public void testListarOrdemAlfabetica() throws Exception {
//
//    }
//
//    @Test
//    public void testBuscarPagarReceber() throws Exception {
//
//    }

    @Test
    public void testListarOrdemTabela() throws Exception {
        
        
        for (PagarReceberClienteParcelamentoDTO p : pgDAO.listarOrdemTabelaTudo()) {
            Object id = p.getId();
            Object data_gerada = p.getData_gerada();
            Object descricao_pagar = p.getDescricao_pagar();
            Object numero_parcelas = p.getNumero_parcelas();
            Object valor_parcelas = p.getValor_parcela();
            Object valor_total = p.getValor_total();
            Object valor_final = p.getValor_final();
            Object juros = p.getJuros();
            Object descricao = p.getDescricao_conta();
            Object nome_razao = p.getNome_razao();
            Object descricao_parcelamento = p.getDescricao_parcelamento();
            
            System.out.println("===================//===================//========================");
            System.out.println(
                    "id :" + id +
                    "\nNome: " + nome_razao +
                    "\nDesrição: " + descricao_parcelamento + 
                    "\nDescrição Conta: " + descricao + 
                    "\nData - Gerada: " + data_gerada +
                    "\nTipo de conta: " +  descricao_pagar +
                    "\nnúmero de parcelas: " + numero_parcelas);
            System.out.println("===================//===================//========================");
        }
    }
}
