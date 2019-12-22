package controller;

import static controleempresa.Controleempresa.stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.RelatoriosService;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class PrincipalController extends Application {
    
    @FXML private MenuItem MenuClose;
    @FXML private MenuItem MenuUsuario;
    @FXML private MenuItem MenuTipoConta;
    @FXML private MenuItem MenuTipoPagamento;
    @FXML private MenuItem MenuTipoTransacao;
    @FXML private MenuItem MenuProdutos;
    @FXML private MenuItem MenuClienteFornecedorFuncionario;
    @FXML private MenuItem MenuContasaPagarReceber;
    @FXML private MenuItem MenuParcelamento;
    @FXML private MenuItem MenuVenda;
    @FXML private MenuItem MenuCaixaEmpresa;
    @FXML private MenuItem MenuSobre;
    
    
    @FXML
    public void onMenuCloseAction(){
        System.exit(0);
    }
    
    RelatoriosService rSER = new RelatoriosService();
    
    @FXML
    public void onMenuUsuarioAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewUsuarios.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Controle de Usuários");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onMenuTipoContaAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewTipoConta.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Tipo de Contas");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onMenuTipoPagamentoAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewTipoPagamento.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Tipo de Pagamentos");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onMenuTipoTransacaoAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewTipoTransacao.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Tipos de Transação");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onMenuProdutosAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewProduto.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Produtos / Estoque");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onMenuCFFAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewClienteFornecedorFuncionario.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Cliente / Fornecedor / Funcionário");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    @FXML
    public void onMenuPRAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewPagarReceber.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Contas a Pagar e Receber");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void onMenuParcelamentoAction(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewParcelamento.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Parcelamento");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
        @FXML
    void onMenuVendaAction(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewVendas.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Vendas");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void AoAcionarrelVendasUnitario(ActionEvent event) {
        String idvenda = JOptionPane.showInputDialog("Digite o Número da Venda que Deseja", "");
        if(idvenda.equals("")){
            Alerts.showAlert("ATENÇÂO", "", "É necessário Digitar um valor numérico", Alert.AlertType.WARNING);
        }else{
            rSER.criarGerarRelatorioVendasUnitario(Long.parseLong(idvenda));
        }
    }
    
    @FXML
    void onMenuCaixaEmpresaAction(ActionEvent event) {
           try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewCaixaEmpresa.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Caixa Empresa");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void onMenuSobreAction(ActionEvent event) {
           try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewSobre.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial - Sobre o Sistema");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
