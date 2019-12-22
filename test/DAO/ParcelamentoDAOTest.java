package DAO;

import DTO.ParcelamentoDTO;
import org.junit.Test;

/**
 * @author MDiasT
 */
public class ParcelamentoDAOTest {
    ParcelamentoDAO pDAO = new ParcelamentoDAO();
    ParcelamentoDTO pDTO = new ParcelamentoDTO();

    public void capturaIncluir(){
        pDTO.setDescricao_parcelamento("Teste de Inserção");
    }

    public void capturaAlterar(){
        pDTO.setId(13l);
        pDTO.setDescricao_parcelamento("Teste de Update");
    }

    @Test
    public void testIncluir() throws Exception {
        capturaIncluir();
        pDAO.incluir(pDTO);
    }

    @Test
    public void testAlterar() throws Exception {
        capturaAlterar();
        pDAO.alterar(pDTO);
    }

    @Test
    public void testExcluir() throws Exception {
        pDAO.excluir(12l);
    }

    @Test
    public void testListarOrdem() throws Exception {
        
        pDAO.listarOrdem();
        
        for (ParcelamentoDTO p : pDAO.listarOrdem()) {
            Object id = p.getId();
            Object descricao = p.getDescricao_parcelamento();
            System.out.println("id: " + id);
            System.out.println("descrição: " + descricao);
        }
        
    }
}
