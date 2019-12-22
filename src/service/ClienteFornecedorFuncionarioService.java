package service;

import DAO.ClienteFornecedorFuncionarioDAO;
import DTO.ClienteFornecedorFuncionarioDTO;
import exception.ApplicationException;
import java.util.List;

/**
 * @author MDT
 */
public class ClienteFornecedorFuncionarioService {
    
    private ClienteFornecedorFuncionarioDAO cffDAO = new ClienteFornecedorFuncionarioDAO();
    
    public void incluir(ClienteFornecedorFuncionarioDTO cff) throws ApplicationException {
        cffDAO.incluir(cff);
    }
    
    public void alterar(ClienteFornecedorFuncionarioDTO cff) throws ApplicationException {
        cffDAO.alterar(cff);
    }
    
    public void excluir(Long id) throws ApplicationException {
        cffDAO.excluir(id);
    }
    
    public List<ClienteFornecedorFuncionarioDTO> listarOrdem() throws ApplicationException {
        return cffDAO.listarOrdem();
    }
    
    public List<ClienteFornecedorFuncionarioDTO> listarOrdemAlfabetica() throws ApplicationException {
        return cffDAO.listarOrdemAlfabetica();
    }
    
    public ClienteFornecedorFuncionarioDTO buscarclientefornecedorfuncionario(String nome_razao) throws ApplicationException {
        if (cffDAO.buscarclientefornecedorfuncionario(nome_razao) == null){
            
        }
        return cffDAO.buscarclientefornecedorfuncionario(nome_razao);
    }
    
    public List<ClienteFornecedorFuncionarioDTO> buscarclientefornecedorfuncionarionome(String nome_razao) throws ApplicationException {
        return cffDAO.buscarclientefornecedorfuncionarionome(nome_razao);
    }
    
    public List<ClienteFornecedorFuncionarioDTO> buscarclientefornecedorfuncionariocodigo(Long id) throws ApplicationException {
        return cffDAO.buscarclientefornecedorfuncionariocodigo(id);
    }
    
    public List<ClienteFornecedorFuncionarioDTO> listarOrdemCombo() throws ApplicationException {
        return cffDAO.listarOrdemCombo();
    }
}
