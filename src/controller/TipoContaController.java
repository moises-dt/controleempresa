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
import DTO.TipoContaDTO;
import javafx.scene.input.MouseEvent;
import service.TipoContaService;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class TipoContaController implements Initializable {

    TipoContaService tcSer = new TipoContaService();
    TipoContaDTO tc = new TipoContaDTO();

    /**
     * Initializes the controller class.
     */
    @FXML private Button btFechar;
    @FXML private Button btExcluir;
    @FXML private Button btAlterarSalva;
    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;

    @FXML private TableView<TipoContaDTO> tabela;
    @FXML private TableColumn<TipoContaDTO, Long> idconta;
    @FXML private TableColumn<TipoContaDTO, String> Descricaocol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableTipoConta();
    }

    public void initTableTipoConta() {
        idconta.setCellValueFactory(new PropertyValueFactory<>("idconta"));
        Descricaocol.setCellValueFactory(new PropertyValueFactory<>("descricao_conta"));

        tabela.setItems(listaDeTipoConta());
    }

    private ObservableList<TipoContaDTO> listaDeTipoConta() {
        try {
            return FXCollections.observableArrayList(tcSer.listar());
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
            if (tcSer.buscarTipoConta(txtDescricao.getText()).getDescricao_conta()!= null) {
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Conta  Já CADASTRADO", Alert.AlertType.WARNING);
            } else if (txtId.getText().length() >= 1) {
                tcSer.alterar(tc);
                LimpaTela();
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Conta já existe ALTERADO com SUCESSO", Alert.AlertType.WARNING);
            } else if (txtId.getText().equals("")){
                tcSer.incluir(tc);
                LimpaTela();
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Conta CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
            }
            initTableTipoConta();
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
                tcSer.excluir(Long.parseLong(txtId.getText()));
                LimpaTela();
                initTableTipoConta();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            txtId.setText(tabela.getSelectionModel().getSelectedItem().getIdconta().toString());
            txtDescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao_conta());
        }
    }

    public void capturarInformações() {
        tc.setDescricao_conta(txtDescricao.getText());
        if (txtId.getText().trim().equals("")) {
            
        } else {
            tc.setIdconta(Long.parseLong(txtId.getText()));
        }
    }
    
    public void LimpaTela(){
        txtDescricao.setText("");
        txtId.setText("");
    }
}
