package service;

import DAO.CaixaDAO;
import DTO.CaixaDTO;
import DTO.CaixaTransacaoPagamentoDTO;
import exception.ApplicationException;
import java.util.Date;
import java.util.List;

/**
 * @author MDiasT
 */
public class CaixaService {
    
    private CaixaDAO cDAO = new CaixaDAO();
    
    public void incluir(CaixaDTO c) throws ApplicationException {
        cDAO.incluir(c);
    }
    
    public void alterar(CaixaDTO c) throws ApplicationException {
        cDAO.alterar(c);
    }
    
    public void excluir(Long id) throws ApplicationException {
        cDAO.excluir(id);
    }
    
    public List<CaixaDTO> listarOrdem() throws ApplicationException {
        return cDAO.listarOrdem();
    }
    
    public List<CaixaTransacaoPagamentoDTO> listarTabela(Date data) throws ApplicationException {
        return cDAO.listarTabela(data);
    }
}
