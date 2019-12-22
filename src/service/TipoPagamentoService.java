package service;

import DAO.TipoPagamentoDAO;
import exception.ApplicationException;
import java.util.List;
import DTO.TipoPagamentoDTO;

/**
 * @author MDT
 */
public class TipoPagamentoService {
    
    private TipoPagamentoDAO tpDao = new TipoPagamentoDAO();
    
    public void incluir(TipoPagamentoDTO tp) throws ApplicationException {
        tpDao.incluir(tp);
    }
    
    public void alterar(TipoPagamentoDTO tp) throws ApplicationException {
        tpDao.alterar(tp);
    }
    
    public void excluir(Long id) throws ApplicationException {
        tpDao.excluir(id);
    }
    
    public List<TipoPagamentoDTO> listar() throws ApplicationException {
        return tpDao.listar();
    }
    
    public TipoPagamentoDTO buscarTipoPagamento(String descricao) throws ApplicationException {
        return tpDao.buscarTipoPagamento(descricao);
    }
    
    public List<TipoPagamentoDTO> listarOrdemCombo() throws ApplicationException {
        return tpDao.listarOrdemCombo();
    }
}
