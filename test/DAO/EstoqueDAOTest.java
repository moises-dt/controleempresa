package DAO;

import DTO.EstoqueDTO;
import DTO.EstoqueProdutoFornecedorDTO;
import java.time.Instant;
import java.util.Date;
import org.junit.Test;
import service.EstoqueService;

/**
 * @author MDT
 */
public class EstoqueDAOTest {
        
    Long idestoque = 1l;
    EstoqueDTO estoque = new EstoqueDTO();
    EstoqueService eSER = new EstoqueService();
    EstoqueProdutoFornecedorDTO epf = new EstoqueProdutoFornecedorDTO();
            
    public void carregaInformações(){
        estoque.setIdestoque(9l);
        estoque.setIdproduto(1l);
        estoque.setIdcliente(2l);
        estoque.setQuantidade(10l);
        estoque.setNotanumero(171l);
        estoque.setData(Date.from(Instant.now()));
    }
    
    public void carregaInformaçõesAlterar(){
        estoque.setIdestoque(9l);
        estoque.setIdproduto(1l);
        estoque.setIdcliente(2l);
        estoque.setQuantidade(10l);
        estoque.setNotanumero(171l);
        estoque.setData(Date.from(Instant.now()));
    }
    
    @Test
    public void testIncluir() throws Exception {
        carregaInformações();
        
        eSER.incluir(estoque);
    }

    @Test
    public void testAlterar() throws Exception {
        
        carregaInformaçõesAlterar();
        
        System.out.println(eSER.listarOrdemPorCodigo(1l).get(0));
    }

    @Test
    public void testExcluir() throws Exception {
        
        eSER.excluir(4l);
    }

    @Test
    public void testListarOrdem() throws Exception {
        System.out.println(eSER.listarOrdem().size());
    }

    @Test
    public void testbuscarIdProduto() throws Exception {
        System.out.println(eSER.buscarIdProduto(idestoque).getIdestoque());
    }

    @Test
    public void testListarOrdemPorCodigo() throws Exception {
        System.out.println(eSER.listarOrdemPorCodigo(3l));
    }
}
