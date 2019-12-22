package DAO;

import DTO.ProdutoDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Assert;
import org.junit.Test;
import service.ProdutoService;

/**
 * @author MDT
 */
public class ProdutoDAOTest {
    
    ProdutoDTO produto = new ProdutoDTO();
    ProdutoService pSER = new ProdutoService();
    
//    public void capturarInformaçõesAlterar(){
//        
//        produto.setCodigo("171B");
//        produto.setDescricao("Teste do Mesmo Alterado");
//        produto.setTipovolume("Volume de Teste Alterado");
//        produto.setCategoria("Categoria de Teste Alterado");
//        produto.setValorcompra(90.0);
//        produto.setComissaovendedor(11.6);
//        produto.setLucro(6.9);
//        produto.setTransportecusto(5.8);
//        produto.setAtivoinativo(false);
//        produto.setValorestipo(false);
//        produto.setValortotalproduto(156.3);
//        produto.setQuantidademinima(15);
//        produto.setQuantidadeatual(100l);
//        produto.setIdproduto(4l);
//    }
//    
//    public void capturarInformações(){
//        
//        produto.setCodigo("171");
//        produto.setDescricao("Teste do Mesmo");
//        produto.setTipovolume("Volume de Teste");
//        produto.setCategoria("Categoria de Teste");
//        produto.setValorcompra(80.0);
//        produto.setComissaovendedor(10.6);
//        produto.setLucro(5.9);
//        produto.setTransportecusto(6.8);
//        produto.setAtivoinativo(true);
//        produto.setValorestipo(true);
//        produto.setValortotalproduto(256.3);
//        produto.setQuantidademinima(10);
//        produto.setQuantidadeatual(130l);
//        produto.setIdproduto(4l);
//    }
    
    public ProdutoDAOTest() {
    }

//    @Test
//    public void testIncluir() throws Exception {
//        capturarInformações();
//        
//        pSER.incluir(produto);
//    }
//
//    @Test
//    public void testAlterar() throws Exception {
//        capturarInformaçõesAlterar();
//        
//        pSER.alterar(produto);
//    }
//
//    @Test
//    public void testUpdateQuantidadeAtual() throws Exception {
//        capturarInformações();
//        
//        pSER.updateQuantidadeAtual(produto);
//    }

    @Test
    public void testExcluir() throws Exception {
        
        pSER.excluir(10l);
    }

    @Test
    public void testListarOrdem() throws Exception {
        
        System.out.println(pSER.listarOrdem().get(1));
    }

    @Test
    public void testListarOrdemAlfabetica() throws Exception {
        
        System.out.println(pSER.listarOrdemAlfabetica().get(7));
        
    }

//    @Test
//    public void testBuscarProduto() throws Exception {
//        
//        ProdutoDTO produto01 = pSER.buscarProduto("Formatação de Computador");
//        
//        assertThat(produto01.getDescricao(), is("Formatação de Computador"));
//    }

    @Test
    public void testListarOrdemCombo() throws Exception {
        
        System.out.println(pSER.listarOrdemCombo().get(2));
    }

    @Test
    public void testBuscarProdutoCodigo() throws Exception {
        
        ProdutoDTO produto01 = pSER.buscarProdutoCodigo("178");
        
        //assertThat(produto01.getCodigo(), is("171"));
        Assert.assertNull(produto01.getCodigo());
    }
}