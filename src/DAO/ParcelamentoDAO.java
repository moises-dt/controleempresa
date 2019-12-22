package DAO;

import DTO.ParcelamentoDTO;
import connection.DataBaseConnection;
import connection.DataBaseConnectionFactory;
import exception.ApplicationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MDiasT
 */
public class ParcelamentoDAO  extends DataBaseConnection {
    
    private static final String SQL_INSERT = " INSERT INTO parcelamento (descricao_parcelamento, taxajuros, quantidadeparcelas, entrada) VALUES (?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE parcelamento SET descricao_parcelamento = ?, taxajuros = ?, quantidadeparcelas = ?, entrada = ? WHERE id = ? ";
    private static final String SQL_DELETE = " DELETE FROM parcelamento WHERE id = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM parcelamento ORDER BY id ";
    private static final String SQL_SELECT_ID = " SELECT * FROM parcelamento WHERE id = ? ORDER BY id ";
    
    public void incluir(ParcelamentoDTO p) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getDescricao_parcelamento());
            ps.setDouble(2, p.getTaxajuros());
            ps.setInt(3, p.getQuantidadeparcelas());
            ps.setBoolean(4, p.getEntrada());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Parcelamento " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public void alterar(ParcelamentoDTO p) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getDescricao_parcelamento());
            ps.setDouble(2, p.getTaxajuros());
            ps.setInt(3, p.getQuantidadeparcelas());
            ps.setBoolean(4, p.getEntrada());
            ps.setLong(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Parcelamento " + ex.getMessage());
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
            throw new ApplicationException("Excluir Parcelamento " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public List<ParcelamentoDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ParcelamentoDTO> lista = new ArrayList<ParcelamentoDTO>();
    
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ParcelamentoDTO p = new ParcelamentoDTO();
                p.setId(rs.getLong("id"));
                p.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                p.setTaxajuros(rs.getDouble("taxajuros"));
                p.setQuantidadeparcelas(rs.getInt("quantidadeparcelas"));
                p.setEntrada(rs.getBoolean("entrada"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Parcelamento por ID " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public ParcelamentoDTO buscarParcelamentoID(Long id) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ParcelamentoDTO p = new ParcelamentoDTO();
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getLong("id"));
                p.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                p.setTaxajuros(rs.getDouble("taxajuros"));
                p.setQuantidadeparcelas(rs.getInt("quantidadeparcelas"));
                p.setEntrada(rs.getBoolean("entrada"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Cliente ou Fornecedor ou Funcionario já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return p;
    }
    
    
    @Override
    public Connection getConnection() throws SQLException {
        return DataBaseConnectionFactory.getConnection();
    }
}
