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
import DTO.TipoPagamentoDTO;

/**
 * @author MDT
 */
public class TipoPagamentoDAO extends DataBaseConnection{
    
    private static final String SQL_INSERT = " INSERT INTO tipopagamento (descricaopagamento) VALUES (?) ";
    private static final String SQL_UPDATE = " UPDATE tipopagamento SET descricaopagamento = ? WHERE idpagamento = ? ";
    private static final String SQL_DELETE = " DELETE FROM tipopagamento WHERE idpagamento = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM tipopagamento Order By idpagamento ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT * FROM tipopagamento WHERE descricaopagamento = ? ";
    

    public void incluir(TipoPagamentoDTO tp) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, tp.getDescricao());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Tipo Pagamento " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(TipoPagamentoDTO tp) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, tp.getDescricao());
            ps.setLong(2, tp.getIdpagamento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Tipo Pagamento " + ex.getMessage());
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
            throw new ApplicationException("Excluir Tipo Pagamento " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<TipoPagamentoDTO> listar() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoPagamentoDTO> lista = new ArrayList<TipoPagamentoDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoPagamentoDTO tp = new TipoPagamentoDTO();
                tp.setIdpagamento(rs.getLong("idpagamento"));
                tp.setDescricao(rs.getString("descricaopagamento"));
                lista.add(tp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Tipo Pagamento " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public TipoPagamentoDTO buscarTipoPagamento(String descricao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        TipoPagamentoDTO tp = new TipoPagamentoDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, descricao);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tp.setDescricao(rs.getString("descricaopagamento"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Tipo de Pagamento já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return tp;
    }
    
    public List<TipoPagamentoDTO> listarOrdemCombo() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoPagamentoDTO> lista = new ArrayList<TipoPagamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoPagamentoDTO tp = new TipoPagamentoDTO();
                tp.setIdpagamento(rs.getLong("idpagamento"));
                tp.setDescricao(rs.getString("descricaopagamento"));
                lista.add(tp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Tipo Pagamento ComboBox " + ex.getMessage());
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
