package service;

import DAO.TipoContaDAO;
import exception.ApplicationException;
import java.util.List;
import DTO.TipoContaDTO;

/**
 * @author MDT
 */
public class TipoContaService {
    
    private TipoContaDAO tcDao = new TipoContaDAO();
    
    public void incluir(TipoContaDTO tc) throws ApplicationException {
        tcDao.incluir(tc);
    }
    
    public void alterar(TipoContaDTO tc) throws ApplicationException {
        tcDao.alterar(tc);
    }
    
    public void excluir(Long id) throws ApplicationException {
        tcDao.excluir(id);
    }
    
    public List<TipoContaDTO> listar() throws ApplicationException {
        return tcDao.listar();
    }
    
    public TipoContaDTO buscarTipoConta(String descricao) throws ApplicationException {
        return tcDao.buscarTipoConta(descricao);
    }
    
}
