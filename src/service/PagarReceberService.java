package service;

import DAO.PagarReceberDAO;
import DTO.PagarReceberClienteParcelamentoDTO;
import DTO.PagarReceberDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDT
 */
public class PagarReceberService {
    
    PagarReceberDAO prDAO = new PagarReceberDAO();
    
    public Long incluir(PagarReceberDTO pr) throws ApplicationException {
            return prDAO.incluir(pr);
    }
    
    public void alterar(PagarReceberDTO pr) throws ApplicationException {
        prDAO.alterar(pr);
    }
    
    public void excluir(Long id) throws ApplicationException {
        prDAO.excluir(id);
    }
    
    public List<PagarReceberDTO> listarOrdem() throws ApplicationException {
        return prDAO.listarOrdem();
    }
    
    public List<PagarReceberDTO> listarOrdemAlfabetica() throws ApplicationException {
        return prDAO.listarOrdemAlfabetica();
    }
    
    public PagarReceberDTO buscarPagarReceber(String descrição) throws ApplicationException {
        return prDAO.buscarPagarReceber(descrição);
    }
    
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaPadrao() throws ApplicationException {
        return prDAO.listarOrdemTabelaPadrao();
    }
    
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaTudo() throws ApplicationException {
        return prDAO.listarOrdemTabelaTudo();
    }
    
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAPagar() throws ApplicationException {
        return prDAO.listarOrdemTabelaAPagar();
    }
    
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAReceber() throws ApplicationException {
        return prDAO.listarOrdemTabelaAReceber();
    }
}
