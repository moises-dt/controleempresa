package service;

import DAO.ParcelamentoDAO;
import DTO.ParcelamentoDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDiasT
 */
public class ParcelamentoService {

    ParcelamentoDAO pDAO = new ParcelamentoDAO();
    
    public void incluir(ParcelamentoDTO p) throws ApplicationException {
        pDAO.incluir(p);
    }

    public void alterar(ParcelamentoDTO p) throws ApplicationException {
        pDAO.alterar(p);
    }
    
    public void excluir(Long id) throws ApplicationException {
        pDAO.excluir(id);
    }
    
    public List<ParcelamentoDTO> listarOrdem() throws ApplicationException {
        return pDAO.listarOrdem();
    }
    
    public ParcelamentoDTO buscarParcelamentoID(Long id) throws ApplicationException {
        return pDAO.buscarParcelamentoID(id);
    }
}
