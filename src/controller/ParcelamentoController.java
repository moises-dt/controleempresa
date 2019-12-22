package controller;

import DTO.ParcelamentoDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ParcelamentoService;
import util.Formatar;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDiasT
 */
public class ParcelamentoController implements Initializable {

    ParcelamentoService pSER = new ParcelamentoService();
    ParcelamentoDTO pDTO = new ParcelamentoDTO();
    Formatar formata = new Formatar();
    
    @FXML private Button btAlterarSalvar;
    @FXML private Button btExcluir;
    @FXML private Button btFechar;
    @FXML private TableView<ParcelamentoDTO> tabela;
    @FXML private TableColumn<ParcelamentoDTO, Long> IdCol;
    @FXML private TableColumn<ParcelamentoDTO, Boolean> EntradaCol;
    @FXML private TableColumn<ParcelamentoDTO, String> DescricaoCol;
    @FXML private TableColumn<ParcelamentoDTO, Double> JurosCol;
    @FXML private TableColumn<ParcelamentoDTO, Integer> ParcelasCol;

    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;
    @FXML private CheckBox ChcEntrada;
    @FXML private TextField txtParcelas;
    @FXML private TextField txtJuros;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableParcelamento();
                
    }    
    
    public void initTableParcelamento() {
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        EntradaCol.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        DescricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao_parcelamento"));
        JurosCol.setCellValueFactory(new PropertyValueFactory<>("taxajuros"));
        ParcelasCol.setCellValueFactory(new PropertyValueFactory<>("quantidadeparcelas"));

        tabela.setItems(listaDeParcelamento());
    }
    
    //Preenche a tabela de Pagar e Receber   
    private ObservableList<ParcelamentoDTO> listaDeParcelamento() {
        try {
            return FXCollections.observableArrayList(pSER.listarOrdem());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @FXML
    void btExcluirAction(ActionEvent event) {
        if(txtId.getText().trim().equals("")){
            Alerts.showAlert("ATENÇÂO", "", "É necessário selecionar a informação a Excluir", Alert.AlertType.WARNING);
        }else{
            try {
                pSER.excluir(Long.parseLong(txtId.getText()));
                initTableParcelamento();
                limparTelaParcelamento();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            txtId.setText(tabela.getSelectionModel().getSelectedItem().getId().toString());
            if(tabela.getSelectionModel().getSelectedItem().getEntrada().equals(true)){
                ChcEntrada.setSelected(true);
            }else{
                ChcEntrada.setSelected(false);
            }
            txtDescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao_parcelamento());
            txtJuros.setText(formata.convertendoDoubleparaDoubleBrasil(tabela.getSelectionModel().getSelectedItem().getTaxajuros().toString()));
            txtParcelas.setText(tabela.getSelectionModel().getSelectedItem().getQuantidadeparcelas().toString());
        }
    }

    @FXML
    void onbtFecharAction(ActionEvent event) {
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
    void onbtSalvarAlterarTPAction(ActionEvent event) {
        capturaInformacoes();
        if(txtId.getText().trim().equals("") && !txtDescricao.getText().trim().equals("")){
            try {
                pSER.incluir(pDTO);
                initTableParcelamento();
                limparTelaParcelamento();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }else if(!txtId.getText().trim().equals("")){
            try {
                pSER.alterar(pDTO);
                initTableParcelamento();
                limparTelaParcelamento();
            } catch (ApplicationException ex) {
                ex.printStackTrace();            }
        }
    }
    
    void capturaInformacoes(){
        if(txtDescricao.getText().trim().equals("") || txtJuros.getText().trim().equals("") || txtParcelas.getText().trim().equals("")){
            Alerts.showAlert("ATENÇÂO", "", "É necessário Digitar as informações nos campos Descrição, ataxa de Juros e Quantidade de Parcelas", Alert.AlertType.WARNING);
        }else{
            if(txtId.getText().trim().equals("")){
            
            }else{
                pDTO.setId(Long.parseLong(txtId.getText()));
            }
            if(ChcEntrada.isSelected()){
                pDTO.setEntrada(true);
             }else{
                pDTO.setEntrada(false);
            }
            pDTO.setDescricao_parcelamento(txtDescricao.getText());
            pDTO.setTaxajuros(Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(txtJuros.getText())));
            pDTO.setQuantidadeparcelas(Integer.parseInt(txtParcelas.getText()));
        }
    }
    
    public void limparTelaParcelamento(){
        txtId.setText("");
        txtDescricao.setText("");
        ChcEntrada.setSelected(false);
        txtParcelas.setText("");
        txtJuros.setText("");
    }
}
