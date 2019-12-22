package service;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDT
 */
public class UsuarioService {

    private UsuarioDAO uDao = new UsuarioDAO();

    public void incluir(UsuarioDTO u) throws ApplicationException {
        uDao.incluir(u);
    }

    public void alterar(UsuarioDTO u) throws ApplicationException {
        uDao.alterar(u);
    }

    public void excluir(String login) throws ApplicationException {
        uDao.excluir(login);
    }

    public UsuarioDTO autenticar(String login, String senha) throws ApplicationException {
        return uDao.autenticar(login, senha);
    }

    public List<UsuarioDTO> listar() throws ApplicationException {
        return uDao.listar();
    }
    
    public UsuarioDTO buscarLogin(String login) throws ApplicationException {
        return uDao.buscarLogin(login);
    }
}
