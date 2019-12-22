package DAO;

import DTO.PagarReceberClienteParcelamentoDTO;
import DTO.PagarReceberDTO;
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
public class PagarReceberDAO extends DataBaseConnection {

    private static final String SQL_INSERT = " INSERT INTO pagar_receber (data_gerada,"
            + "descricao_pagar, numero_parcelas, valor_parcela, valor_total, valor_final, juros,"
            + "id_tipo_conta, id_cliente, id_parcelamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    private static final String SQL_UPDATE = " UPDATE pagar_receber SET id = ?,"
            + "descricao_pagar = ?, numero_parcelas = ?, valor_parcela = ?, valor_total = ?, valor_final = ?,"
            + "juros = ?, id_tipo_conta = ?, id_cliente = ?, id_parcelamento = ? WHERE id = ? ";
    private static final String SQL_DELETE = " DELETE FROM pagar_receber WHERE id = ? ";
    private static final String SQL_SELECT_ALL = " SELECT * FROM pagar_receber Order By id ";
    private static final String SQL_SELECT_ALL_ALFABETICA = " SELECT * FROM pagar_receber ORDER BY descricao ";
    private static final String SQL_SELECT_BUSCA_DESCRICAO = " SELECT * FROM pagar_receber WHERE descricao = ? ";
    private static final String SQL_SELECT_BUSCA_TABELA_PADRAO = " SELECT pg.id, pg.data_gerada, pg.numero_parcelas, "
            + " pg.valor_parcela, pg.valor_total, pg.valor_final, pg.juros, tc.descricao_conta, pg.descricao_pagar, "
            + " c.nome_razao, p.descricao_parcelamento, ppr.id_parcela, ppr.id_pagar_receber, ppr.parcela_numero, "
            + " ppr.data_vencimento, ppr.pago, ppr.valor_pago, ppr.data_pagamento FROM pagar_receber pg INNER JOIN tipoconta tc "
            + " ON pg.id_tipo_conta = tc.idconta INNER JOIN clientefornecedorfuncionario c ON pg.id_cliente = c.id "
            + " LEFT JOIN parcelamento p ON pg.id_parcelamento = p.id LEFT JOIN parcelas_p_r ppr ON ppr.id_pagar_receber = pg.id "
            + " WHERE ppr.pago = false ORDER BY ppr.data_vencimento ";
    private static final String SQL_SELECT_BUSCA_TABELA_TUDO = " SELECT pg.id, pg.data_gerada, pg.numero_parcelas, pg.valor_parcela, "
            + " pg.valor_total, pg.valor_final, pg.juros, tc.descricao_conta, pg.descricao_pagar, c.nome_razao, p.descricao_parcelamento, "
            + " ppr.id_parcela, ppr.id_pagar_receber, ppr.parcela_numero, ppr.data_vencimento, ppr.pago, ppr.valor_pago, ppr.data_pagamento "
            + " FROM pagar_receber pg INNER JOIN tipoconta tc ON pg.id_tipo_conta = tc.idconta INNER JOIN clientefornecedorfuncionario c "
            + " ON pg.id_cliente = c.id LEFT JOIN parcelamento p ON pg.id_parcelamento = p.id LEFT JOIN parcelas_p_r ppr ON ppr.id_pagar_receber = pg.id "
            + " ORDER BY ppr.data_vencimento ";
    private static final String SQL_SELECT_BUSCA_TABELA_PAGAR = " SELECT pg.id, pg.data_gerada, pg.numero_parcelas, pg.valor_parcela, pg.valor_total, "
            + " pg.valor_final, pg.juros, tc.descricao_conta, pg.descricao_pagar, c.nome_razao, p.descricao_parcelamento, ppr.id_parcela, ppr.id_pagar_receber, "
            + " ppr.parcela_numero, ppr.data_vencimento, ppr.pago, ppr.valor_pago, ppr.data_pagamento FROM pagar_receber pg INNER JOIN tipoconta tc "
            + " ON pg.id_tipo_conta = tc.idconta INNER JOIN clientefornecedorfuncionario c ON pg.id_cliente = c.id LEFT JOIN parcelamento p ON pg.id_parcelamento = p.id "
            + " LEFT JOIN parcelas_p_r ppr ON ppr.id_pagar_receber = pg.id WHERE tc.descricao_conta='A Pagar' ORDER BY ppr.data_vencimento ";
    private static final String SQL_SELECT_BUSCA_TABELA_RECEBER = " SELECT pg.id, pg.data_gerada, pg.numero_parcelas, pg.valor_parcela, pg.valor_total, pg.valor_final, "
            + " pg.juros, tc.descricao_conta, pg.descricao_pagar, c.nome_razao, p.descricao_parcelamento, ppr.id_parcela, ppr.id_pagar_receber, ppr.parcela_numero, "
            + " ppr.data_vencimento, ppr.pago, ppr.valor_pago, ppr.data_pagamento FROM pagar_receber pg INNER JOIN tipoconta tc ON pg.id_tipo_conta = tc.idconta "
            + " INNER JOIN clientefornecedorfuncionario c ON pg.id_cliente = c.id LEFT JOIN parcelamento p ON pg.id_parcelamento = p.id LEFT JOIN parcelas_p_r ppr "
            + " ON ppr.id_pagar_receber = pg.id WHERE tc.descricao_conta='A Receber' ORDER BY ppr.data_vencimento ";

    public Long incluir(PagarReceberDTO pr) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        Long id = 0l;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            if (pr.getData_gerada() != null) {
                ps.setDate(1, new java.sql.Date(pr.getData_gerada().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, pr.getDescricao_pagar());
            ps.setLong(3, pr.getNumero_parcelas());
            ps.setDouble(4, pr.getValor_parcela());
            ps.setDouble(5, pr.getValor_total());
            ps.setDouble(6, pr.getValor_final());
            ps.setDouble(7, pr.getJuros());
            ps.setLong(8, pr.getId_tipo_conta());
            ps.setLong(9, pr.getId_cliente());
            ps.setLong(10, pr.getId_parcelamento());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Incluir em Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
        return id;
    }

    public void alterar(PagarReceberDTO pr) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            if (pr.getData_gerada() != null) {
                ps.setDate(1, new java.sql.Date(pr.getData_gerada().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, pr.getDescricao_pagar());
            ps.setLong(3, pr.getNumero_parcelas());
            ps.setDouble(4, pr.getValor_parcela());
            ps.setDouble(5, pr.getValor_total());
            ps.setDouble(6, pr.getValor_final());
            ps.setDouble(7, pr.getJuros());
            ps.setLong(8, pr.getId_tipo_conta());
            ps.setLong(9, pr.getId_cliente());
            ps.setLong(10, pr.getId_parcelamento());
            ps.setLong(11, pr.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ApplicationException("Alterar Pagar e Receber " + ex.getMessage());
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
            throw new ApplicationException("Excluir Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, null);
        }
    }

    public List<PagarReceberDTO> listarOrdem() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberDTO> lista = new ArrayList<PagarReceberDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberDTO pr = new PagarReceberDTO();
                pr.setId(rs.getLong("id"));
                pr.setData_gerada(rs.getDate("data_gerada"));
                pr.setDescricao_pagar(rs.getString("descricao_pagar"));
                pr.setNumero_parcelas(rs.getLong("numero_parcelas"));
                pr.setValor_parcela(rs.getDouble("valor_parcela"));
                pr.setValor_total(rs.getDouble("valor_total"));
                pr.setValor_final(rs.getDouble("valor_final"));
                pr.setJuros(rs.getDouble("juros"));
                pr.setId_tipo_conta(rs.getLong("id_tipo_conta"));
                pr.setId_cliente(rs.getLong("id_cliente"));
                pr.setId_parcelamento(rs.getLong("id_parcelamento"));
                lista.add(pr);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public List<PagarReceberDTO> listarOrdemAlfabetica() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberDTO> lista = new ArrayList<PagarReceberDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL_ALFABETICA);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberDTO pr = new PagarReceberDTO();
                pr.setId(rs.getLong("id"));
                pr.setData_gerada(rs.getDate("data_gerada"));
                pr.setNumero_parcelas(rs.getLong("valor_parcelas"));
                pr.setValor_parcela(rs.getDouble("valor_parcela"));
                pr.setValor_total(rs.getDouble("valor_total"));
                pr.setValor_final(rs.getDouble("valor_final"));
                pr.setJuros(rs.getDouble("juros"));
                pr.setId_tipo_conta(rs.getLong("id_tipo_conta"));
                pr.setDescricao_pagar(rs.getString("descricao_pagar"));
                pr.setId_cliente(rs.getLong("id_cliente"));
                pr.setId_cliente(rs.getLong("id_parcelamento"));
                lista.add(pr);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber em ordem alfabética " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    public PagarReceberDTO buscarPagarReceber(String descrição) throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        PagarReceberDTO pr = new PagarReceberDTO();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_DESCRICAO);
            ps.setString(1, descrição);
            rs = ps.executeQuery();
            while (rs.next()) {
                pr.setDescricao_pagar(rs.getString("descricao_pagar"));
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Buscando Pagar e Receber já CADASTRADO " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return pr;
    }

    //Busca e preenchimento da lista da tabela de contas a pagar e receber da tela Só os não pagos
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaPadrao() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberClienteParcelamentoDTO> lista = new ArrayList<PagarReceberClienteParcelamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_TABELA_PADRAO);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberClienteParcelamentoDTO prcp = new PagarReceberClienteParcelamentoDTO();
                prcp.setId(rs.getLong("id"));
                prcp.setData_gerada(rs.getDate("data_gerada"));
                prcp.setDescricao_pagar(rs.getString("descricao_pagar"));
                prcp.setNumero_parcelas(rs.getLong("numero_parcelas"));
                prcp.setValor_parcela(rs.getDouble("valor_parcela"));
                prcp.setValor_total(rs.getDouble("valor_total"));
                prcp.setValor_final(rs.getDouble("valor_final"));
                prcp.setJuros(rs.getDouble("juros"));
                prcp.setDescricao_conta(rs.getString("descricao_conta"));
                prcp.setNome_razao(rs.getString("nome_razao"));
                prcp.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                prcp.setId_parcela(rs.getLong("id_parcela"));
                prcp.setId_pagar_receber(rs.getLong("id_pagar_receber"));
                prcp.setParcela_numero(rs.getString("parcela_numero"));
                prcp.setData_vencimento(rs.getDate("data_vencimento"));
                prcp.setPago(rs.getBoolean("pago"));
                prcp.setValor_pago(rs.getDouble("valor_pago"));
                prcp.setData_pagamento(rs.getDate("data_pagamento"));
                lista.add(prcp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    //Busca e preenchimento da lista da tabela de contas a pagar e receber da tela Todos
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaTudo() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberClienteParcelamentoDTO> lista = new ArrayList<PagarReceberClienteParcelamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_TABELA_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberClienteParcelamentoDTO prcp = new PagarReceberClienteParcelamentoDTO();
                prcp.setId(rs.getLong("id"));
                prcp.setData_gerada(rs.getDate("data_gerada"));
                prcp.setDescricao_pagar(rs.getString("descricao_pagar"));
                prcp.setNumero_parcelas(rs.getLong("numero_parcelas"));
                prcp.setValor_parcela(rs.getDouble("valor_parcela"));
                prcp.setValor_total(rs.getDouble("valor_total"));
                prcp.setValor_final(rs.getDouble("valor_final"));
                prcp.setJuros(rs.getDouble("juros"));
                prcp.setDescricao_conta(rs.getString("descricao_conta"));
                prcp.setNome_razao(rs.getString("nome_razao"));
                prcp.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                prcp.setId_parcela(rs.getLong("id_parcela"));
                prcp.setId_pagar_receber(rs.getLong("id_pagar_receber"));
                prcp.setParcela_numero(rs.getString("parcela_numero"));
                prcp.setData_vencimento(rs.getDate("data_vencimento"));
                prcp.setPago(rs.getBoolean("pago"));
                prcp.setValor_pago(rs.getDouble("valor_pago"));
                prcp.setData_pagamento(rs.getDate("data_pagamento"));
                lista.add(prcp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    //Busca e preenchimento da lista da tabela de contas a pagar e receber da tela Só os A Pagar
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAPagar() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberClienteParcelamentoDTO> lista = new ArrayList<PagarReceberClienteParcelamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_TABELA_PAGAR);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberClienteParcelamentoDTO prcp = new PagarReceberClienteParcelamentoDTO();
                prcp.setId(rs.getLong("id"));
                prcp.setData_gerada(rs.getDate("data_gerada"));
                prcp.setDescricao_pagar(rs.getString("descricao_pagar"));
                prcp.setNumero_parcelas(rs.getLong("numero_parcelas"));
                prcp.setValor_parcela(rs.getDouble("valor_parcela"));
                prcp.setValor_total(rs.getDouble("valor_total"));
                prcp.setValor_final(rs.getDouble("valor_final"));
                prcp.setJuros(rs.getDouble("juros"));
                prcp.setDescricao_conta(rs.getString("descricao_conta"));
                prcp.setNome_razao(rs.getString("nome_razao"));
                prcp.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                prcp.setId_parcela(rs.getLong("id_parcela"));
                prcp.setId_pagar_receber(rs.getLong("id_pagar_receber"));
                prcp.setParcela_numero(rs.getString("parcela_numero"));
                prcp.setData_vencimento(rs.getDate("data_vencimento"));
                prcp.setPago(rs.getBoolean("pago"));
                prcp.setValor_pago(rs.getDouble("valor_pago"));
                prcp.setData_pagamento(rs.getDate("data_pagamento"));
                lista.add(prcp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber " + ex.getMessage());
        } finally {
            close(con, ps, rs);
        }
        return lista;
    }

    //Busca e preenchimento da lista da tabela de contas a pagar e receber da tela Só os a Receber
    public List<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAReceber() throws ApplicationException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PagarReceberClienteParcelamentoDTO> lista = new ArrayList<PagarReceberClienteParcelamentoDTO>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BUSCA_TABELA_RECEBER);
            rs = ps.executeQuery();
            while (rs.next()) {
                PagarReceberClienteParcelamentoDTO prcp = new PagarReceberClienteParcelamentoDTO();
                prcp.setId(rs.getLong("id"));
                prcp.setData_gerada(rs.getDate("data_gerada"));
                prcp.setDescricao_pagar(rs.getString("descricao_pagar"));
                prcp.setNumero_parcelas(rs.getLong("numero_parcelas"));
                prcp.setValor_parcela(rs.getDouble("valor_parcela"));
                prcp.setValor_total(rs.getDouble("valor_total"));
                prcp.setValor_final(rs.getDouble("valor_final"));
                prcp.setJuros(rs.getDouble("juros"));
                prcp.setDescricao_conta(rs.getString("descricao_conta"));
                prcp.setNome_razao(rs.getString("nome_razao"));
                prcp.setDescricao_parcelamento(rs.getString("descricao_parcelamento"));
                prcp.setId_parcela(rs.getLong("id_parcela"));
                prcp.setId_pagar_receber(rs.getLong("id_pagar_receber"));
                prcp.setParcela_numero(rs.getString("parcela_numero"));
                prcp.setData_vencimento(rs.getDate("data_vencimento"));
                prcp.setPago(rs.getBoolean("pago"));
                prcp.setValor_pago(rs.getDouble("valor_pago"));
                prcp.setData_pagamento(rs.getDate("data_pagamento"));
                lista.add(prcp);
            }
        } catch (SQLException ex) {
            throw new ApplicationException("Listar Contas a Pagar e Receber " + ex.getMessage());
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
