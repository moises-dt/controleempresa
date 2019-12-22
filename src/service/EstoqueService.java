package service;

import DAO.EstoqueDAO;
import DTO.EstoqueDTO;
import DTO.EstoqueProdutoFornecedorDTO;
import exception.ApplicationException;
import java.util.Date;
import java.util.List;

/**
 * @author MDT
 */
public class EstoqueService {
    
    EstoqueDAO eDAO = new EstoqueDAO();
    
    public void incluir(EstoqueDTO e) throws ApplicationException {
        eDAO.incluir(e);
    }
    
    public void alterar(EstoqueDTO e) throws ApplicationException {
        eDAO.alterar(e);
    }
    
    public void excluir(Long id) throws ApplicationException {
        eDAO.excluir(id);
    }
    
    public List<EstoqueDTO> listarOrdem() throws ApplicationException {
        return eDAO.listarOrdem();
    }
    
    public EstoqueDTO buscarIdProduto(Long idestoque) throws ApplicationException {
        return eDAO.buscarIdProduto(idestoque);
    }
    
    public List<EstoqueProdutoFornecedorDTO> listarOrdemPorCodigo(Long idproduto) throws ApplicationException {
        return eDAO.listarOrdemPorCodigo(idproduto);
    }
    
}
