/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DTO.VendasDTO;
import java.time.Instant;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MDiasT
 */
public class VendasServiceTest {
    
    VendasService vSER = new VendasService();
    VendasDTO vDTO = new VendasDTO();
    
    void capturaInformacoes(){
        vDTO.setIdcliente(3l);
        vDTO.setData(Date.from(Instant.now()));
        vDTO.setValortotal(1800.0);
        vDTO.setIdvenda(22l);
    }
    
    public VendasServiceTest() {
    }

    @Test
    public void testIncluir() throws Exception {
    }

    @Test
    public void testAlterar() throws Exception {
        capturaInformacoes();
        vSER.alterar(vDTO);
    }

    @Test
    public void testExcluir() throws Exception {
    }

    @Test
    public void testListarOrdem() throws Exception {
    }

    @Test
    public void testListarOrdemTabela() throws Exception {
        
    }
    
}
