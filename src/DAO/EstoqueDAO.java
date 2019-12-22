package DAO;

import DTO.EstoqueDTO;
import DTO.EstoqueProdutoFornecedorDTO;
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
 * @author MDT
 */
public class EstoqueDAO extends DataBaseConnection {

    private static final String SQL_INSERT = " INSERT INTO estoque (idproduto,"
            + "idcliente, quantidade, notanumero, data) VALUES (?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE estoque SET idproduto = ?,"
            + "idcliente = ?, quantidade = ?, notanumero = ?, data = ? WHERE idestoque = ? ";
    private static final String SQL_DELETE = " DELETE FROM estoque WHERE idestoque = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM estoque Order By data ";
    private static final String SQL_SELECT_BUSCA = " SELECT * FROM estoque WHERE idestoque = ? ";
    private static final String SQL_SELECT_BUSCA_IDPRODUTO = " SELECT e.idestoque, c.nome_razao,"
            + " p.descricao, e.quantidade, e.notanumero, e.data FROM estoque e INNER JOIN produto p"
            + " ON e.idproduto = p.idproduto JOIN clientefornecedorfuncionario c ON e.idcliente = c.id"
            + " WHERE e.idproduto = ? ORDER BY data DESC, idestoque DESC ";

    public void incluir(EstoqueDTO e) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setLong(1, e.getIdproduto());
            ps.setLong(2, e.getIdcliente());
            ps.setLong(3, e.getQuantidade());
            ps.setLong(4, e.getNotanumero());
            if (e.getData() != null) {
                ps.setDate(5, new java.sql.Date(e.getData().getTime()));
            } else {
                ps.setDate(5, null);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Estoque " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(EstoqueDTO e) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setLong(1, e.getIdproduto());
            ps.setLong(2, e.getIdcliente());
            ps.setLong(3, e.getQuantidade());
            ps.setLong(4, e.getNotanumero());
            if (e.getData() != null) {
                ps.setDate(5, new java.sql.Date(e.getData().getTime()));
            } else {
                ps.setDate(5, null);
            }
            ps.setLong(6, e.getIdestoque());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Estoque " + ex.getMessage());
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

    public List<EstoqueDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EstoqueDTO> lista = new ArrayList<EstoqueDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstoqueDTO e = new EstoqueDTO();
                e.setIdestoque(rs.getLong("idestoque"));
                e.setIdproduto(rs.getLong("idproduto"));
                e.setIdcliente(rs.getLong("idcliente"));
                e.setQuantidade(rs.getLong("quantidade"));
                e.setNotanumero(rs.getLong("notanumero"));
                e.setData(rs.getDate("data"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Estoque por Data " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public EstoqueDTO buscarIdProduto(Long idestoque) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        EstoqueDTO e = new EstoqueDTO();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA);
            ps.setLong(1, idestoque);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.setIdestoque(rs.getLong("idestoque"));
                e.setIdproduto(rs.getLong("idproduto"));
                e.setIdcliente(rs.getLong("idcliente"));
                e.setQuantidade(rs.getLong("quantidade"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Estoque por ID " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return e;
    }

    //Lista de Produtos e fornecedores com base no estoque
    public List listarOrdemPorCodigo(Long idproduto) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List listatudo = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_IDPRODUTO);
            ps.setLong(1, idproduto);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstoqueProdutoFornecedorDTO epf = new EstoqueProdutoFornecedorDTO();
                epf.setIdestoque(rs.getLong("idestoque"));
                epf.setDescricao(rs.getString("descricao"));
                epf.setNome_razao(rs.getString("nome_razao"));
                epf.setQuantidade(rs.getLong("quantidade"));
                epf.setNotanumero(rs.getLong("notanumero"));
                epf.setData(rs.getDate("data"));
                listatudo.add(epf);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Estoque Pelo Id do Produto " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return listatudo;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataBaseConnectionFactory.getConnection();
    }
}
