/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.time.Instant;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import service.CaixaService;

/**
 *
 * @author MDiasT
 */
public class CaixaDAOTest {
    
    private CaixaService cSER = new CaixaService();

    @Test
    public void testIncluir() throws Exception {
    }

    @Test
    public void testAlterar() throws Exception {
    }

    @Test
    public void testExcluir() throws Exception {
    }

    @Test
    public void testListarOrdem() throws Exception {
    }

    @Test
    public void testListarTabela() throws Exception {
        
        for (int i = 0; i < cSER.listarTabela(Date.from(Instant.now())).size(); i++) {
            System.out.println(cSER.listarTabela(Date.from(Instant.now())).get(i).getDescricaotransacao());
        }
        
    }
}
