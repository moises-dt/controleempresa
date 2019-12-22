package service;

import DAO.ProdutoDAO;
import exception.ApplicationException;
import java.util.List;
import DTO.ProdutoDTO;

/**
 * @author MDT
 */
public class ProdutoService {
    
    private ProdutoDAO pDao = new ProdutoDAO();
    
    public void incluir(ProdutoDTO p) throws ApplicationException {
        pDao.incluir(p);
    }
    
    public void alterar(ProdutoDTO p) throws ApplicationException {
        pDao.alterar(p);
    }
    
    public void updateQuantidadeAtual(ProdutoDTO p) throws ApplicationException {
        pDao.updateQuantidadeAtual(p);
    }
    
    public void excluir(Long idproduto) throws ApplicationException {
        pDao.excluir(idproduto);
    }
    
    public List<ProdutoDTO> listarOrdem() throws ApplicationException {
        return pDao.listarOrdem();
    }
    
    public List<ProdutoDTO> listarOrdemAlfabetica() throws ApplicationException {
        return pDao.listarOrdemAlfabetica();
    }
    
    public List<ProdutoDTO> buscarProdutoDescricao(String descricao) throws ApplicationException {
        return pDao.buscarProdutoDescricao(descricao);
    }
    
    public ProdutoDTO buscarProduto(String descricao) throws ApplicationException {
        return pDao.buscarProduto(descricao);
    }
    
    public List<ProdutoDTO> listarOrdemCombo() throws ApplicationException {
        return pDao.listarOrdemCombo();
    }
    
    public ProdutoDTO buscarProdutoId(Long idproduto) throws ApplicationException {
        return pDao.buscarProdutoId(idproduto);
    }
    
    public ProdutoDTO buscarProdutoCodigo(String codigo) throws ApplicationException {
        return pDao.buscarProdutoCodigo(codigo);
    }
}
