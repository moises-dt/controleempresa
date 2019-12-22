package DAO;

import DTO.ParcelasPRDTO;
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
public class ParcelasPRDAO extends DataBaseConnection {
    
    
    private static final String SQL_INSERT = " INSERT INTO parcelas_p_r (id_pagar_receber,"
            + " parcela_numero, data_vencimento, pago, valor_pago, data_pagamento) "
            + " VALUES (?, ?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = " UPDATE parcelas_p_r SET id_parcelas = ?, "
            + " parcela_numero = ?, data_vencimento = ?, pago = ?, valor_pago = ?, data_pagamento = ? "
            + " WHERE id_parcela = ? ";
    private static final String SQL_DELETE = " DELETE FROM parcelas_p_r WHERE id_pagar_receber = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM parcelas_p_r Order By id_parcela ";
    
    
    public void incluir(ParcelasPRDTO ppr) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setLong(1, ppr.getId_pagar_receber());
            ps.setString(2, ppr.getParcela_numero());
            if (ppr.getData_vencimento() != null) {
                ps.setDate(3, new java.sql.Date(ppr.getData_vencimento().getTime()));
            } else {
                ps.setDate(3, null);
            }
            ps.setBoolean(4, ppr.getPago());
            if(ppr.getValor_pago() != null){
                ps.setDouble(5, ppr.getValor_pago());
            }else{
                ps.setDouble(5, 0.0);
            }
           if (ppr.getData_pagamento() != null) {
                ps.setDate(6, new java.sql.Date(ppr.getData_pagamento().getTime()));
            } else {
                ps.setDate(6, null);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Parcelas de Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public void alterar(ParcelasPRDTO ppr) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setLong(1, ppr.getId_pagar_receber());
            ps.setString(2, ppr.getParcela_numero());
            if (ppr.getData_vencimento() != null) {
                ps.setDate(3, new java.sql.Date(ppr.getData_vencimento().getTime()));
            } else {
                ps.setDate(3, null);
            }
            ps.setBoolean(4, ppr.getPago());
            ps.setDouble(5, ppr.getValor_pago());
           if (ppr.getData_pagamento() != null) {
                ps.setDate(6, new java.sql.Date(ppr.getData_pagamento().getTime()));
            } else {
                ps.setDate(6, null);
            }
            ps.setLong(7, ppr.getId_parcela());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Parcelas de Pagar e Receber " + ex.getMessage());
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
            throw new ApplicationException("Excluir Parcelas de Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }
    
    public List<ParcelasPRDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ParcelasPRDTO> lista = new ArrayList<ParcelasPRDTO>();
    
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ParcelasPRDTO ppr = new ParcelasPRDTO();
                ppr.setId_parcela(rs.getLong("id_parcela"));
                ppr.setId_pagar_receber(rs.getLong("id_pagar_receber"));
                ppr.setParcela_numero(rs.getString("parcela_numero"));
                ppr.setData_vencimento(rs.getDate("data_vencimento"));
                ppr.setPago(rs.getBoolean("pago"));
                ppr.setValor_pago(rs.getDouble("valor_pago"));
                ppr.setData_pagamento(rs.getDate("data_pagamento"));
                lista.add(ppr);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Parcelas de Contas a Pagar e Receber " + ex.getMessage());
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
