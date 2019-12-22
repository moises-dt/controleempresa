package service;

import DAO.VendaItensDAO;
import DTO.VendaItensDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDiasT
 */
public class VendaItensService {

    VendaItensDAO viDAO = new VendaItensDAO();

    public void incluir(VendaItensDTO vi) throws ApplicationException {
        viDAO.incluir(vi);
    }

    public void alterar(VendaItensDTO vi) throws ApplicationException {
        viDAO.alterar(vi);
    }

    public void excluir(Long id) throws ApplicationException {
        viDAO.excluir(id);
    }

    public List<VendaItensDTO> listarOrdem() throws ApplicationException {
        return viDAO.listarOrdem();
    }
}
