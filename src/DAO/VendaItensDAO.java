package DAO;

import DTO.VendaItensDTO;
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
public class VendaItensDAO  extends DataBaseConnection {

    private static final String SQL_INSERT = " INSERT INTO vendaitens ( idproduto,"
            + " quantidade, valor, idvenda) VALUES (?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE vendaitens SET idproduto = ?, "
            + " quantidade = ?, valor = ?, idvenda = ? WHERE idvendaitens = ? ";
    private static final String SQL_DELETE = " DELETE FROM vendaitens WHERE idvendaitens = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM vendaitens ";
    
    public void incluir(VendaItensDTO vi) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setDouble(1, vi.getIdproduto());
            ps.setLong(2, vi.getQuantidade());
            ps.setDouble(3, vi.getValor());
            ps.setLong(4, vi.getIdvenda());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Venda Itens " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public void alterar(VendaItensDTO vi) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setDouble(1, vi.getIdproduto());
            ps.setLong(2, vi.getQuantidade());
            ps.setDouble(3, vi.getValor());
            ps.setLong(4, vi.getIdvenda());
            ps.setLong(5, vi.getIdvendaitens());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Venda Itens " + ex.getMessage());
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
            throw new ApplicationException("Excluir Itens de Venda " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public List<VendaItensDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<VendaItensDTO> lista = new ArrayList<VendaItensDTO>();
    
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendaItensDTO vi = new VendaItensDTO();
                vi.setIdvendaitens(rs.getLong("idvendaitens"));
                vi.setIdproduto(rs.getLong("idproduto"));
                vi.setQuantidade(rs.getInt("quantidade"));
                vi.setValor(rs.getDouble("valor"));
                vi.setIdvenda(rs.getLong("idvenda"));
                lista.add(vi);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Venda itens " + ex.getMessage());
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
