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
import DTO.TipoTransacaoDTO;
import javafx.scene.input.MouseEvent;
import service.TipoTransacaoService;
import view.util.Alerts;

/**
 * FXML Controller class
 *
 * @author MDT
 */
public class TipoTransacaoController implements Initializable {
    TipoTransacaoService ttSer = new TipoTransacaoService();
    TipoTransacaoDTO tt = new TipoTransacaoDTO();
    
    @FXML private Button btFechar;
    @FXML private Button btExcluir;
    @FXML private Button btAlterarSalva;
    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;
    @FXML private TableView<TipoTransacaoDTO> tabela;
    @FXML private TableColumn<TipoTransacaoDTO, Long> idtransacao;
    @FXML private TableColumn<TipoTransacaoDTO, String> Descricaocol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableTipoPagamento();
    }    
    
    public void initTableTipoPagamento() {
        idtransacao.setCellValueFactory(new PropertyValueFactory<>("idtransacao"));
        Descricaocol.setCellValueFactory(new PropertyValueFactory<>("Descricao"));

        tabela.setItems(listaDeTipoTransacao());
    }

    private ObservableList<TipoTransacaoDTO> listaDeTipoTransacao() {
        try {
            return FXCollections.observableArrayList(ttSer.listar());
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
            if (ttSer.buscarTipoTransacao(txtDescricao.getText()).getDescricao() != null) {
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Pagamento  Já CADASTRADO", Alert.AlertType.WARNING);
            } else if (txtId.getText().length() >= 1) {
                ttSer.alterar(tt);
                Alerts.showAlert("ATENÇÂO", "", "Descricao de Tipo de Pagamento já existe ALTERADO com SUCESSO", Alert.AlertType.WARNING);
            } else if (txtId.getText().equals("")){
                ttSer.incluir(tt);
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
                ttSer.excluir(Long.parseLong(txtId.getText()));
                initTableTipoPagamento();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            txtId.setText(tabela.getSelectionModel().getSelectedItem().getIdtransacao().toString());
            txtDescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao());
        }
    }

    public void capturarInformações() {
        tt.setDescricao(txtDescricao.getText());
        if (txtId.getText().trim().equals("")) {
            
        } else {
            tt.setIdtransacao(Long.parseLong(txtId.getText()));
        }
    }
    
}
