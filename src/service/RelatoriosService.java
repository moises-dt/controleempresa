package service;

import connection.DataBaseConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author MDiasT
 */
public class RelatoriosService {
    
    
    public void criarGerarRelatorioVendasUnitario(Long idvenda) {
        Connection con;

        try {
            con = DataBaseConnectionFactory.getConnection();
            String src = "C:/Users/MDiasT/Documents/NetBeansProjects/controleempresa/src/relatorio/RelatorioVendasNumero.jasper";
            JasperPrint jasperprint = null;
            try {
                HashMap numero = new HashMap();
                numero.put("idvenda", idvenda);
                jasperprint = JasperFillManager.fillReport(src, numero, con);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            JasperViewer view = new JasperViewer(jasperprint, false);
            view.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void criarGerarRelatorioVendaPeriodo(Date dataInicial, Date dataFinal) {
        Connection con;

        try {
            con = DataBaseConnectionFactory.getConnection();
            String src = "C:/Users/MDiasT/Documents/NetBeansProjects/controleempresa/src/relatorio/RelatorioVendasPeriodo.jasper";
            JasperPrint jasperprint = null;
            try {
                HashMap numero = new HashMap();
                numero.put("dataInicial", dataInicial);
                numero.put("dataFinal", dataFinal);
                jasperprint = JasperFillManager.fillReport(src, numero, con);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            JasperViewer view = new JasperViewer(jasperprint, false);
            view.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
}
