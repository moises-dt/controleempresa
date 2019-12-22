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
import DTO.ProdutoDTO;

/**
 * @author MDT
 */
public class ProdutoDAO extends DataBaseConnection {

    private static final String SQL_INSERT = " INSERT INTO produto (codigo, descricao,"
            + " tipovolume, categoria, valorcompra, comissaovendedor, lucro, transportecusto,"
            + " ativoinativo, valorestipo, valortotalproduto, quantidademinima)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE produto SET codigo = ?, descricao = ?,"
            + " tipovolume = ?, categoria = ?, valorcompra = ?, comissaovendedor = ?, lucro = ?,"
            + " transportecusto = ?, ativoinativo = ?, valorestipo = ?, valortotalproduto = ?,"
            + " quantidademinima = ? WHERE idproduto = ? ";
    private static final String SQL_UPDATE_QUANTIDADE_PRODUTO = " UPDATE produto SET quantidadeatual = ?"
            + " WHERE idproduto = ?";
    private static final String SQL_DELETE = " DELETE FROM produto WHERE idproduto = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM produto Order By idproduto ";
    private static final String SQL_SELECT_ALL_ALFABETICA = " SELECT * FROM produto ORDER BY descricao ";
    private static final String SQL_SELECT_BUSCA = " SELECT * FROM produto WHERE descricao ? ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT DISTINCT idproduto, codigo, descricao,"
            + "tipovolume, categoria, valorcompra, comissaovendedor, lucro, transportecusto, ativoinativo,"
            + "valorestipo, valortotalproduto, quantidademinima, quantidadeatual FROM produto "
            + "WHERE UPPER(descricao) ILIKE ? ";
    private static final String SQL_SELECT_BUSCA_ID = " SELECT * FROM produto WHERE idproduto = ? ";
    private static final String SQL_SELECT_BUSCA_CODIGO = " SELECT * FROM produto WHERE codigo = ? ";

    public void incluir(ProdutoDTO p) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getDescricao());
            ps.setString(3, p.getTipovolume());
            ps.setString(4, p.getCategoria());
            ps.setDouble(5, p.getValorcompra());
            ps.setDouble(6, p.getComissaovendedor());
            ps.setDouble(7, p.getLucro());
            ps.setDouble(8, p.getTransportecusto());
            ps.setBoolean(9, p.getAtivoinativo());
            ps.setBoolean(10, p.getValorestipo());
            ps.setDouble(11, p.getValortotalproduto());
            ps.setInt(12, p.getQuantidademinima());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Produto " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(ProdutoDTO p) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getDescricao());
            ps.setString(3, p.getTipovolume());
            ps.setString(4, p.getCategoria());
            ps.setDouble(5, p.getValorcompra());
            ps.setDouble(6, p.getComissaovendedor());
            ps.setDouble(7, p.getLucro());
            ps.setDouble(8, p.getTransportecusto());
            ps.setBoolean(9, p.getAtivoinativo());
            ps.setBoolean(10, p.getValorestipo());
            ps.setDouble(11, p.getValortotalproduto());
            ps.setInt(12, p.getQuantidademinima());
            ps.setLong(13, p.getIdproduto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Produto " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    //Entrade de produto em estoque
    public void updateQuantidadeAtual(ProdutoDTO p) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE_QUANTIDADE_PRODUTO);
            ps.setLong(1, p.getQuantidadeatual());
            ps.setLong(2, p.getIdproduto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Quantidade Atual no Estoque " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void excluir(Long idproduto) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setLong(1, idproduto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Excluir Produto " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<ProdutoDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setIdproduto(rs.getLong("idproduto"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipovolume(rs.getString("tipovolume"));
                p.setCategoria(rs.getString("categoria"));
                p.setValorcompra(rs.getDouble("valorcompra"));
                p.setComissaovendedor(rs.getDouble("comissaovendedor"));
                p.setLucro(rs.getDouble("lucro"));
                p.setTransportecusto(rs.getDouble("transportecusto"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                p.setValorestipo(rs.getBoolean("valorestipo"));
                p.setValortotalproduto(rs.getDouble("valortotalproduto"));
                p.setQuantidademinima(rs.getInt("quantidademinima"));
                p.setQuantidadeatual(rs.getLong("quantidadeatual"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Produtos em ordem numérica " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public List<ProdutoDTO> listarOrdemAlfabetica() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL_ALFABETICA);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setIdproduto(rs.getLong("idproduto"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipovolume(rs.getString("tipovolume"));
                p.setCategoria(rs.getString("categoria"));
                p.setValorcompra(rs.getDouble("valorcompra"));
                p.setComissaovendedor(rs.getDouble("comissaovendedor"));
                p.setLucro(rs.getDouble("lucro"));
                p.setTransportecusto(rs.getDouble("transportecusto"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                p.setValorestipo(rs.getBoolean("valorestipo"));
                p.setValortotalproduto(rs.getDouble("valortotalproduto"));
                p.setQuantidademinima(rs.getInt("quantidademinima"));
                p.setQuantidadeatual(rs.getLong("quantidadeatual"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Produtos em ordem alfabética " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public List<ProdutoDTO> buscarProdutoDescricao(String descricao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ProdutoDTO> lista = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, "%" + descricao + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setIdproduto(rs.getLong("idproduto"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipovolume(rs.getString("tipovolume"));
                p.setCategoria(rs.getString("categoria"));
                p.setValorcompra(rs.getDouble("valorcompra"));
                p.setComissaovendedor(rs.getDouble("comissaovendedor"));
                p.setLucro(rs.getDouble("lucro"));
                p.setTransportecusto(rs.getDouble("transportecusto"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                p.setValorestipo(rs.getBoolean("valorestipo"));
                p.setValortotalproduto(rs.getDouble("valortotalproduto"));
                p.setQuantidademinima(rs.getInt("quantidademinima"));
                p.setQuantidadeatual(rs.getLong("quantidadeatual"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando descrição de Produto já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public ProdutoDTO buscarProduto(String descricao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ProdutoDTO p = new ProdutoDTO();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA);
            ps.setString(1, descricao);
            rs = ps.executeQuery();

            while (rs.next()) {
                p.setIdproduto(rs.getLong("idproduto"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipovolume(rs.getString("tipovolume"));
                p.setCategoria(rs.getString("categoria"));
                p.setValorcompra(rs.getDouble("valorcompra"));
                p.setComissaovendedor(rs.getDouble("comissaovendedor"));
                p.setLucro(rs.getDouble("lucro"));
                p.setTransportecusto(rs.getDouble("transportecusto"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                p.setValorestipo(rs.getBoolean("valorestipo"));
                p.setValortotalproduto(rs.getDouble("valortotalproduto"));
                p.setQuantidademinima(rs.getInt("quantidademinima"));
                p.setQuantidadeatual(rs.getLong("quantidadeatual"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando descrição de Produto já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return p;
    }

    public List<ProdutoDTO> listarOrdemCombo() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setIdproduto(rs.getLong("idproduto"));
                p.setDescricao(rs.getString("descricao"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Produtos em ordem numérica ComboBox " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public ProdutoDTO buscarProdutoId(Long idproduto) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ProdutoDTO p = new ProdutoDTO();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_ID);
            ps.setLong(1, idproduto);
            rs = ps.executeQuery();

            while (rs.next()) {
                p.setIdproduto(rs.getLong("idproduto"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipovolume(rs.getString("tipovolume"));
                p.setCategoria(rs.getString("categoria"));
                p.setValorcompra(rs.getDouble("valorcompra"));
                p.setComissaovendedor(rs.getDouble("comissaovendedor"));
                p.setLucro(rs.getDouble("lucro"));
                p.setTransportecusto(rs.getDouble("transportecusto"));
                p.setAtivoinativo(rs.getBoolean("ativoinativo"));
                p.setValorestipo(rs.getBoolean("valorestipo"));
                p.setValortotalproduto(rs.getDouble("valortotalproduto"));
                p.setQuantidademinima(rs.getInt("quantidademinima"));
                p.setQuantidadeatual(rs.getLong("quantidadeatual"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Id do Produto " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return p;
    }

    public ProdutoDTO buscarProdutoCodigo(String codigo) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ProdutoDTO p = new ProdutoDTO();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_CODIGO);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            while (rs.next()) {
                p.setCodigo(rs.getString("codigo"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Código de Produto já CADASTRADO " + ex.getMessage());
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
