package DAO;

import DTO.ClienteFornecedorFuncionarioDTO;
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
public class ClienteFornecedorFuncionarioDAO extends DataBaseConnection{
    
    private static final String SQL_INSERT = " INSERT INTO clientefornecedorfuncionario (cpf_cnpj,"
            + "nome_razao, endereco, numero, complemento, bairro, cidade, cep, uf, email, site, telefone,"
            + "telefone1, data_cadastro, cliente, fornecedor, funcionario, sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE clientefornecedorfuncionario SET cpf_cnpj = ?,"
            + "nome_razao = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, "
            + "cep = ?, uf = ?, email = ?, site = ?, telefone = ?, telefone1 = ?, data_cadastro = ?,"
            + "cliente = ?, fornecedor = ?, funcionario = ?, sexo = ? WHERE id=? ";
    private static final String SQL_DELETE = " DELETE FROM clientefornecedorfuncionario WHERE id = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM clientefornecedorfuncionario Order By id ";
    private static final String SQL_SELECT_ALL_ALFABETICA = " SELECT * FROM clientefornecedorfuncionario ORDER BY nome_razao ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT * FROM clientefornecedorfuncionario WHERE nome_razao = ? ";
    private static final String SQL_SELECT_BUSCA_NOME = " SELECT DISTINCT id, cpf_cnpj, nome_razao, endereco, numero, complemento,"
            + " bairro, cidade, cep, uf, email, site, telefone, telefone1, data_cadastro, cliente, fornecedor, funcionario, sexo "
            + " FROM clientefornecedorfuncionario WHERE UPPER(nome_razao) ILIKE ? ";
    private static final String SQL_SELECT_BUSCA_CODIGO = " SELECT * FROM clientefornecedorfuncionario WHERE id = ? ";
    
    public void incluir(ClienteFornecedorFuncionarioDTO cff) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, cff.getCpf_cnpj());
            ps.setString(2, cff.getNome_razao());
            ps.setString(3, cff.getEndereco());
            ps.setInt(4, cff.getNumero());
            ps.setString(5, cff.getComplemento());
            ps.setString(6, cff.getBairro());
            ps.setString(7, cff.getCidade());
            ps.setString(8, cff.getCep());
            ps.setString(9, cff.getUf());
            ps.setString(10, cff.getEmail());
            ps.setString(11, cff.getSite());
            ps.setString(12, cff.getTelefone());
            ps.setString(13, cff.getTelefone1());
            if (cff.getData_cadastro() != null) {
                ps.setDate(14, new java.sql.Date(cff.getData_cadastro().getTime()));
            } else {
                ps.setDate(14, null);
            }
            ps.setBoolean(15, cff.getCliente());
            ps.setBoolean(16, cff.getFornecedor());
            ps.setBoolean(17, cff.getFuncionario());
            ps.setString(18, cff.getSexo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Cliente ou Fornecedor ou Funcionario " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(ClienteFornecedorFuncionarioDTO cff) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, cff.getCpf_cnpj());
            ps.setString(2, cff.getNome_razao());
            ps.setString(3, cff.getEndereco());
            ps.setInt(4, cff.getNumero());
            ps.setString(5, cff.getComplemento());
            ps.setString(6, cff.getBairro());
            ps.setString(7, cff.getCidade());
            ps.setString(8, cff.getCep());
            ps.setString(9, cff.getUf());
            ps.setString(10, cff.getEmail());
            ps.setString(11, cff.getSite());
            ps.setString(12, cff.getTelefone());
            ps.setString(13, cff.getTelefone1());
            if (cff.getData_cadastro() != null) {
                ps.setDate(14, new java.sql.Date(cff.getData_cadastro().getTime()));
            } else {
                ps.setDate(14, null);
            }
            ps.setLong(15, cff.getId());
            ps.setBoolean(15, cff.getCliente());
            ps.setBoolean(16, cff.getFornecedor());
            ps.setBoolean(17, cff.getFuncionario());
            ps.setString(18, cff.getSexo());
            ps.setLong(19, cff.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Cliente ou Fornecedor ou Funcionario  " + ex.getMessage());
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
            throw new ApplicationException("Excluir Cliente ou Fornecedor ou Funcionario  " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<ClienteFornecedorFuncionarioDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ClienteFornecedorFuncionarioDTO> lista = new ArrayList<ClienteFornecedorFuncionarioDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
                cff.setId(rs.getLong("id"));
                cff.setCpf_cnpj(rs.getString("cpf_cnpj"));
                cff.setNome_razao(rs.getString("nome_razao"));
                cff.setEndereco(rs.getString("endereco"));
                cff.setNumero(rs.getInt("numero"));
                cff.setComplemento(rs.getString("complemento"));
                cff.setBairro(rs.getString("bairro"));
                cff.setCidade(rs.getString("cidade"));
                cff.setCep(rs.getString("cep"));
                cff.setUf(rs.getString("uf"));
                cff.setEmail(rs.getString("email"));
                cff.setSite(rs.getString("site"));
                cff.setTelefone(rs.getString("telefone"));
                cff.setTelefone1(rs.getString("telefone1"));
                cff.setData_cadastro(rs.getDate("data_cadastro"));
                cff.setCliente(rs.getBoolean("cliente"));
                cff.setFornecedor(rs.getBoolean("fornecedor"));
                cff.setFuncionario(rs.getBoolean("funcionario"));
                cff.setSexo(rs.getString("sexo"));
                lista.add(cff);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Cliente ou Fornecedor ou Funcionario em ordem numérica " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public List<ClienteFornecedorFuncionarioDTO> listarOrdemAlfabetica() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ClienteFornecedorFuncionarioDTO> lista = new ArrayList<ClienteFornecedorFuncionarioDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL_ALFABETICA);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
                cff.setId(rs.getLong("id"));
                cff.setCpf_cnpj(rs.getString("cpf_cnpj"));
                cff.setNome_razao(rs.getString("nome_razao"));
                cff.setEndereco(rs.getString("endereco"));
                cff.setNumero(rs.getInt("numero"));
                cff.setComplemento(rs.getString("complemento"));
                cff.setBairro(rs.getString("bairro"));
                cff.setCidade(rs.getString("cidade"));
                cff.setCep(rs.getString("cep"));
                cff.setUf(rs.getString("uf"));
                cff.setEmail(rs.getString("email"));
                cff.setSite(rs.getString("site"));
                cff.setTelefone(rs.getString("telefone"));
                cff.setTelefone1(rs.getString("telefone1"));
                cff.setData_cadastro(rs.getDate("data_cadastro"));
                cff.setCliente(rs.getBoolean("cliente"));
                cff.setFornecedor(rs.getBoolean("fornecedor"));
                cff.setFuncionario(rs.getBoolean("funcionario"));
                cff.setSexo(rs.getString("sexo"));
                lista.add(cff);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Cliente ou Fornecedor ou Funcionario em ordem alfabética " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public ClienteFornecedorFuncionarioDTO buscarclientefornecedorfuncionario(String nome_razao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, nome_razao);
            rs = ps.executeQuery();
            while (rs.next()) {
                cff.setId(rs.getLong("id"));
                cff.setNome_razao(rs.getString("nome_razao"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Cliente ou Fornecedor ou Funcionario já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return cff;
    }
    
    public List<ClienteFornecedorFuncionarioDTO> buscarclientefornecedorfuncionarionome(String nome_razao) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<ClienteFornecedorFuncionarioDTO> lista = new ArrayList<>();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_NOME);
            ps.setString(1, "%"+nome_razao+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
                cff.setId(rs.getLong("id"));
                cff.setCpf_cnpj(rs.getString("cpf_cnpj"));
                cff.setNome_razao(rs.getString("nome_razao"));
                cff.setEndereco(rs.getString("endereco"));
                cff.setNumero(rs.getInt("numero"));
                cff.setComplemento(rs.getString("complemento"));
                cff.setBairro(rs.getString("bairro"));
                cff.setCidade(rs.getString("cidade"));
                cff.setCep(rs.getString("cep"));
                cff.setUf(rs.getString("uf"));
                cff.setEmail(rs.getString("email"));
                cff.setSite(rs.getString("site"));
                cff.setTelefone(rs.getString("telefone"));
                cff.setTelefone1(rs.getString("telefone1"));
                cff.setData_cadastro(rs.getDate("data_cadastro"));
                cff.setCliente(rs.getBoolean("cliente"));
                cff.setFornecedor(rs.getBoolean("fornecedor"));
                cff.setFuncionario(rs.getBoolean("funcionario"));
                cff.setSexo(rs.getString("sexo"));
                lista.add(cff);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Cliente ou Fornecedor ou Funcionario já CADASTRADO por Nome " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public List<ClienteFornecedorFuncionarioDTO> buscarclientefornecedorfuncionariocodigo(Long id) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<ClienteFornecedorFuncionarioDTO> lista = new ArrayList<>();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_CODIGO);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
                cff.setId(rs.getLong("id"));
                cff.setCpf_cnpj(rs.getString("cpf_cnpj"));
                cff.setNome_razao(rs.getString("nome_razao"));
                cff.setEndereco(rs.getString("endereco"));
                cff.setNumero(rs.getInt("numero"));
                cff.setComplemento(rs.getString("complemento"));
                cff.setBairro(rs.getString("bairro"));
                cff.setCidade(rs.getString("cidade"));
                cff.setCep(rs.getString("cep"));
                cff.setUf(rs.getString("uf"));
                cff.setEmail(rs.getString("email"));
                cff.setSite(rs.getString("site"));
                cff.setTelefone(rs.getString("telefone"));
                cff.setTelefone1(rs.getString("telefone1"));
                cff.setData_cadastro(rs.getDate("data_cadastro"));
                cff.setCliente(rs.getBoolean("cliente"));
                cff.setFornecedor(rs.getBoolean("fornecedor"));
                cff.setFuncionario(rs.getBoolean("funcionario"));
                cff.setSexo(rs.getString("sexo"));
                lista.add(cff);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Cliente ou Fornecedor ou Funcionario já CADASTRADO por Código " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public List<ClienteFornecedorFuncionarioDTO> listarOrdemCombo() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ClienteFornecedorFuncionarioDTO> lista = new ArrayList<ClienteFornecedorFuncionarioDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
                cff.setId(rs.getLong("id"));
                cff.setNome_razao(rs.getString("nome_razao"));
                cff.setFornecedor(rs.getBoolean("fornecedor"));
                if (cff.getFornecedor() == true){
                lista.add(cff);
                }
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Cliente ou Fornecedor ou Funcionario em ordem numérica ComboBox " + ex.getMessage());
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
