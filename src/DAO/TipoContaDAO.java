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
import DTO.TipoContaDTO;

/**
 * @author MDT
 */
public class TipoContaDAO  extends DataBaseConnection{
    
    private static final String SQL_INSERT = " INSERT INTO tipoconta(descricao_conta) VALUES (?) ";
    private static final String SQL_UPDATE = " UPDATE tipoconta SET descricao_conta = ? WHERE idconta = ? ";
    private static final String SQL_DELETE = " DELETE FROM tipoconta WHERE idconta = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM tipoconta Order By idconta ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT * FROM tipoconta WHERE descricao_conta = ? ";
    

    public void incluir(TipoContaDTO tc) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, tc.getDescricao_conta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Tipo Conta " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(TipoContaDTO tc) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, tc.getDescricao_conta());
            ps.setLong(2, tc.getIdconta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Tipo Conta " + ex.getMessage());
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
            throw new ApplicationException("Excluir Tipo Conta " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<TipoContaDTO> listar() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoContaDTO> lista = new ArrayList<TipoContaDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoContaDTO tc = new TipoContaDTO();
                tc.setIdconta(rs.getLong("idconta"));
                tc.setDescricao_conta(rs.getString("descricao_conta"));
                lista.add(tc);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Tipo Conta " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public TipoContaDTO buscarTipoConta(String descricao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        TipoContaDTO tc = new TipoContaDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, descricao);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tc.setDescricao_conta(rs.getString("descricao_conta"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Tipo de Conta já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return tc;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataBaseConnectionFactory.getConnection();
    }
}
