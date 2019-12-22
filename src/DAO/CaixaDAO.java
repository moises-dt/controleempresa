package DAO;

import DTO.CaixaDTO;
import DTO.CaixaTransacaoPagamentoDTO;
import connection.DataBaseConnection;
import connection.DataBaseConnectionFactory;
import exception.ApplicationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MDiasT
 */
public class CaixaDAO extends DataBaseConnection {
    
    private static final String SQL_INSERT = " INSERT INTO caixa (data, descricao, "
            + " valortotal, idtransacao, idpagamento) VALUES (?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE caixa SET data = ?,"
            + "descricao = ?, valortotal = ?, idtransacao = ?, idpagamento = ? WHERE idcaixa = ? ";
    private static final String SQL_DELETE = " DELETE FROM caixa WHERE idcaixa = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM caixa WHERE data = ? ORDER BY idcaixa ";
    private static final String SQL_SELECT_TABELA = " SELECT c.idcaixa, c.data, c.descricao, c.valortotal, "
            + " t.descricaotransacao, p.descricaopagamento FROM caixa c INNER JOIN tipotransacao t ON c.idtransacao = t.idtransacao "
            + " INNER JOIN tipopagamento p ON c.idpagamento = p.idpagamento WHERE data = ? ORDER BY c.idcaixa DESC ";
    
    public void incluir(CaixaDTO c) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            if (c.getData() != null) {
                ps.setDate(1, new java.sql.Date(c.getData().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, c.getDescricao());
            ps.setDouble(3, c.getValortotal());
            ps.setLong(4, c.getIdtransacao());
            ps.setLong(5, c.getIdpagamento());
            ps.executeUpdate();
            } catch (SQLException ex) {
              throw new ApplicationException("Incluir em Caixa Empresa " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
        
    public void alterar(CaixaDTO c) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            if (c.getData()!= null) {
                ps.setDate(1, new java.sql.Date(c.getData().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, c.getDescricao());
            ps.setDouble(3, c.getValortotal());
            ps.setLong(4, c.getIdtransacao());
            ps.setLong(5, c.getIdpagamento());
            ps.setLong(6, c.getIdcaixa());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Caixa Empresa " + ex.getMessage());
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
            throw new ApplicationException("Excluir Estoque " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public List<CaixaDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<CaixaDTO> lista = new ArrayList<CaixaDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                CaixaDTO c = new CaixaDTO();
                c.setIdcaixa(rs.getLong("idcaixa"));
                c.setData(rs.getDate("data"));
                c.setDescricao(rs.getString("descricao"));
                c.setValortotal(rs.getDouble("valortotal"));
                c.setIdtransacao(rs.getLong("idtransacao"));
                c.setIdpagamento(rs.getLong("idpagamento"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Caixa Empresa por Data " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public List<CaixaTransacaoPagamentoDTO> listarTabela(Date data) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        
        List<CaixaTransacaoPagamentoDTO> lista = new ArrayList<CaixaTransacaoPagamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_TABELA);
            ps.setDate(1, dataSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CaixaTransacaoPagamentoDTO c = new CaixaTransacaoPagamentoDTO();
                c.setIdcaixa(rs.getLong("idcaixa"));
                c.setData(rs.getDate("data"));
                c.setDescricao(rs.getString("descricao"));
                c.setValortotal(rs.getDouble("valortotal"));
                c.setDescricaotransacao(rs.getString("descricaotransacao"));
                c.setDescricaopagamento(rs.getString("descricaopagamento"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Caixa Empresa por Data Tabela " + ex.getMessage());
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
