package controller;

import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import DTO.TipoPagamentoDTO;
import javafx.scene.input.MouseEvent;
import service.TipoPagamentoService;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class TipoPagamentoController implements Initializable {

    TipoPagamentoService tpSer = new TipoPagamentoService();
    TipoPagamentoDTO tp = new TipoPagamentoDTO();
    
    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btFechar;
    @FXML private Button btExcluir;
    @FXML private Button btAlterarSalva;

    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;
    @FXML private TableView<TipoPagamentoDTO> tabela;
    @FXML private TableColumn<TipoPagamentoDTO, Long>idpagamento;
    @FXML private TableColumn<TipoPagamentoDTO, String>Descricaocol;
    
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        initTableTipoPagamento();
    }    
    
    public void initTableTipoPagamento() {
        idpagamento.setCellValueFactory(new PropertyValueFactory<>("idpagamento"));
        Descricaocol.setCellValueFactory(new PropertyValueFactory<>("Descricao"));

        tabela.setItems(listaDeTipoPagamento());
    }

    private ObservableList<TipoPagamentoDTO> listaDeTipoPagamento() {
        try {
            return FXCollections.observableArrayList(tpSer.listar());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void onbtFecharAction() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ViewPrincipal.fxml"));
            Scene sc = new Scene(parent);
            stage.setTitle("Sistema de Controle Empresarial");
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onbtSalvarAlterarTPAction() {
        capturarInformações();
        try {
            if (tpSer.buscarTipoPagamento(txtDescricao.getText()).getDescricao() != null) {
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Pagamento  Já CADASTRADO", Alert.AlertType.WARNING);
            } else if (txtId.getText().length() >= 1) {
                tpSer.alterar(tp);
                limparaTela();
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Pagamento já existe ALTERADO com SUCESSO", Alert.AlertType.WARNING);
            } else if (txtId.getText().equals("")){
                tpSer.incluir(tp);
                limparaTela();
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Pagamento CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
            }
            initTableTipoPagamento();
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void btExcluirAction() {
        try {
            if (txtId.getText().equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o que deseja excluir", Alert.AlertType.WARNING);
            } else {
                tpSer.excluir(Long.parseLong(txtId.getText()));
                limparaTela();
                initTableTipoPagamento();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            txtId.setText(tabela.getSelectionModel().getSelectedItem().getIdpagamento().toString());
            txtDescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao());
        }
    }

    public void capturarInformações() {
        tp.setDescricao(txtDescricao.getText());
        if (txtId.getText().trim().equals("")) {
            
        } else {
            tp.setIdpagamento(Long.parseLong(txtId.getText()));
        }
    }
    
    public void limparaTela(){
        txtDescricao.setText("");
        txtId.setText("");
    }
    
}
