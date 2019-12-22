package service;

import DAO.ParcelasPRDAO;
import DTO.ParcelasPRDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDiasT
 */
public class ParcelasPRService {
    
    ParcelasPRDAO pprDAO = new ParcelasPRDAO();
    
    public void incluir(ParcelasPRDTO ppr) throws ApplicationException {
        pprDAO.incluir(ppr);
    }
    
    public void alterar(ParcelasPRDTO ppr) throws ApplicationException {
        pprDAO.alterar(ppr);
    }
    
    public void excluir(Long id) throws ApplicationException {
        pprDAO.excluir(id);
    }
    
    public List<ParcelasPRDTO> listarOrdem() throws ApplicationException {
        return pprDAO.listarOrdem();
    }
}
