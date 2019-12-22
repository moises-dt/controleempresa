/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DataBaseConnection;
import connection.DataBaseConnectionFactory;
import exception.ApplicationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.TipoTransacaoDTO;

/**
 *
 * @author MDT
 */
public class TipoTransacaoDAO extends DataBaseConnection{
    
    private static final String SQL_INSERT = " INSERT INTO tipotransacao (descricaotransacao) VALUES (?) ";
    private static final String SQL_UPDATE = " UPDATE tipotransacao SET descricaotransacao = ? WHERE idtransacao = ? ";
    private static final String SQL_DELETE = " DELETE FROM tipotransacao WHERE idtransacao = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM tipotransacao Order By idtransacao ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT * FROM tipotransacao WHERE descricaotransacao = ? ";
    

    public void incluir(TipoTransacaoDTO tt) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, tt.getDescricao());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Tipo Transação " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(TipoTransacaoDTO tt) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, tt.getDescricao());
            ps.setLong(2, tt.getIdtransacao());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Tipo Transação " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public void excluir(Long id) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Excluir Tipo Transação " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<TipoTransacaoDTO> listar() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoTransacaoDTO> lista = new ArrayList<TipoTransacaoDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoTransacaoDTO tt = new TipoTransacaoDTO();
                tt.setIdtransacao(rs.getLong("idtransacao"));
                tt.setDescricao(rs.getString("descricaotransacao"));
                lista.add(tt);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Tipo Transação " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public TipoTransacaoDTO buscarTipoTransacao(String descricao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        TipoTransacaoDTO tt = new TipoTransacaoDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, descricao);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tt.setDescricao(rs.getString("descricaotransacao"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Tipo de Transação já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return tt;
    }
    
    public List<TipoTransacaoDTO> listarOrdemCombo() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoTransacaoDTO> lista = new ArrayList<TipoTransacaoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoTransacaoDTO tt = new TipoTransacaoDTO();
                tt.setIdtransacao(rs.getLong("idtransacao"));
                tt.setDescricao(rs.getString("descricaotransacao"));
                lista.add(tt);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Tipo Transação ComboBox " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataBaseConnectionFactory.getConnection();
    }
    
}
