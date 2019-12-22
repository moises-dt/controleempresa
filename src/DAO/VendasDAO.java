package DAO;

import DTO.VendaItensVendaDTO;
import DTO.VendasDTO;
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
public class VendasDAO extends DataBaseConnection {

    private static final String SQL_INSERT = " INSERT INTO venda (idcliente, "
            + "data, valortotal) VALUES (?, ?, ?) RETURNING idvenda ";
    private static final String SQL_UPDATE = " UPDATE venda SET idcliente = ?, "
            + " data = ? , valortotal = ? WHERE idvenda = ? ";
    private static final String SQL_DELETE = " DELETE FROM venda WHERE idvenda = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM venda ";
    private static final String SQL_SELECT_VENDAITENS = " SELECT v.idvenda, vi.idvendaitens, "
            + " c.nome_razao, v.data, p.descricao, vi.quantidade, vi.valor "
            + " FROM vendaitens vi INNER JOIN venda v ON v.idvenda = vi.idvenda JOIN "
            + " clientefornecedorfuncionario c ON c.id = v.idcliente JOIN produto p "
            + " ON p.idproduto = vi.idproduto WHERE vi.idvenda = ? ";

    
    public Long incluir(VendasDTO v) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        Long idvenda = 0l;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setLong(1, v.getIdcliente());
            if (v.getData()!= null) {
                ps.setDate(2, new java.sql.Date(v.getData().getTime()));
            } else {
                ps.setDate(2, null);
            }
            ps.setDouble(3, v.getValortotal());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idvenda = rs.getLong("idvenda");
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Vendas " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
        return idvenda;
    }
    
    public void alterar(VendasDTO v) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setLong(1, v.getIdcliente());
            if (v.getData()!= null) {
                ps.setDate(2, new java.sql.Date(v.getData().getTime()));
            } else {
                ps.setDate(2, null);
            }
            ps.setDouble(3, v.getValortotal());
            ps.setLong(4, v.getIdvenda());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Vendas " + ex.getMessage());
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
            throw new ApplicationException("Excluir Vendas " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public List<VendasDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<VendasDTO> lista = new ArrayList<VendasDTO>();
    
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendasDTO v = new VendasDTO();
                v.setIdvenda(rs.getLong("idvenda"));
                v.setIdcliente(rs.getLong("idcliente"));
                v.setData(rs.getDate("data"));
                v.setValortotal(rs.getDouble("valortotal"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Venda itens " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public List<VendaItensVendaDTO> listarOrdemTabela(Long id) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<VendaItensVendaDTO> lista = new ArrayList<VendaItensVendaDTO>();
    
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_VENDAITENS);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendaItensVendaDTO viv = new VendaItensVendaDTO();
                viv.setIdvenda(rs.getLong("idvenda"));
                viv.setIdvendaitens(rs.getLong("idvendaitens"));
                viv.setNome_razao(rs.getString("nome_razao"));
                viv.setData(rs.getDate("data"));
                viv.setQuantidade(rs.getInt("quantidade"));
                viv.setDescricao(rs.getString("descricao"));
                viv.setValor(rs.getDouble("valor"));
                lista.add(viv);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Venda itens Com CLiente e Produtos " + ex.getMessage());
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
