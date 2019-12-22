package DAO;

import connection.DataBaseConnection;
import connection.DataBaseConnectionFactory;
import DTO.UsuarioDTO;
import exception.ApplicationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author MDT
 */
public class UsuarioDAO extends DataBaseConnection{

    private static final String SQL_INSERT = " INSERT INTO usuario(login, nome, senha, email, situacao) VALUES (?, ?, MD5(?), ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE usuario SET nome=?, senha=MD5(?), email=?, situacao=? WHERE login=? ";
    private static final String SQL_DELETE = " DELETE FROM usuario WHERE login=? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM usuario ";
    private static final String SQL_SELECT_BUSCA_LOGIN = " SELECT * FROM usuario WHERE login = ? ";
    private static final String SQL_SELECT_BUSCA_LOGIN_SENHA = " SELECT * FROM usuario WHERE login = ? and senha = MD5(?) ";
    

    public void incluir(UsuarioDTO u) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getSenha());
            ps.setString(4, u.getEmail());
            ps.setBoolean(5, u.getSituacao());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir Usuários " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public void alterar(UsuarioDTO u) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setString(3, u.getEmail());
            ps.setBoolean(4, u.getSituacao());
            ps.setString(5, u.getLogin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Usuario " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public void excluir(String login) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Excluir Usuario " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public UsuarioDTO autenticar(String login, String senha) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        UsuarioDTO u = new UsuarioDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_LOGIN_SENHA);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setSituacao(rs.getBoolean("situacao"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Autenticar Login e Senha " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return u;
    }
    
    public List<UsuarioDTO> listar() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

        try {

            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setEmail(rs.getString("email"));
                u.setSituacao(rs.getBoolean("situacao"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Usuario " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }
    
    public UsuarioDTO buscarLogin(String login) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        UsuarioDTO u = new UsuarioDTO();
        
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                u.setLogin(rs.getString("login"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Login já cadastrado " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return u;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataBaseConnectionFactory.getConnection();
    }
}
