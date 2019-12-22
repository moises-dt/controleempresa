package service;

import DAO.VendasDAO;
import DTO.VendaItensVendaDTO;
import DTO.VendasDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDiasT
 */
public class VendasService {
    
    VendasDAO vDAO = new VendasDAO();
    
    public Long incluir(VendasDTO v) throws ApplicationException {
        return vDAO.incluir(v);
    }
    
    public void alterar(VendasDTO v) throws ApplicationException {
        vDAO.alterar(v);
    }
    
    public void excluir(Long id) throws ApplicationException {
        vDAO.excluir(id);
    }
    
    public List<VendasDTO> listarOrdem() throws ApplicationException {
        return vDAO.listarOrdem();
    }
    
    public List<VendaItensVendaDTO> listarOrdemTabela(Long id) throws ApplicationException {
        return vDAO.listarOrdemTabela(id);
    }
}
