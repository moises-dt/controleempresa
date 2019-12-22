/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.TipoTransacaoDAO;
import exception.ApplicationException;
import java.util.List;
import DTO.TipoTransacaoDTO;

/**
 *
 * @author MDT
 */
public class TipoTransacaoService {
    
    private TipoTransacaoDAO ttDao = new TipoTransacaoDAO();
    
    public void incluir(TipoTransacaoDTO tt) throws ApplicationException {
        ttDao.incluir(tt);
    }
    
    public void alterar(TipoTransacaoDTO tt) throws ApplicationException {
        ttDao.alterar(tt);
    }
    
    public void excluir(Long id) throws ApplicationException {
        ttDao.excluir(id);
    }
    
    public List<TipoTransacaoDTO> listar() throws ApplicationException {
        return ttDao.listar();
    }
    
    public TipoTransacaoDTO buscarTipoTransacao(String descricao) throws ApplicationException {
        return ttDao.buscarTipoTransacao(descricao);
    }
    
    public List<TipoTransacaoDTO> listarOrdemCombo() throws ApplicationException {
        return ttDao.listarOrdemCombo();
    }
}
