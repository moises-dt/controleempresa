package controller;

import DTO.CaixaDTO;
import DTO.CaixaTransacaoPagamentoDTO;
import DTO.TipoPagamentoDTO;
import DTO.TipoTransacaoDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.CaixaService;
import service.TipoPagamentoService;
import service.TipoTransacaoService;
import util.Conversor;
import util.Formatar;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDiasT
 */
public class CaixaEmpresaController implements Initializable {

    CaixaTransacaoPagamentoDTO ctpDTO = new CaixaTransacaoPagamentoDTO();
    CaixaDTO cDTO = new CaixaDTO();
    CaixaService cSER = new CaixaService();
    TipoTransacaoService ttSER = new TipoTransacaoService();
    TipoPagamentoService tpSER = new TipoPagamentoService();
    Double valorsoma=0.0, valor=0.0;
    Long id_pagamento = 0l, id_transacao = 0l;
    
    Conversor conversor = new Conversor();
    Formatar formata = new Formatar();
    
    @FXML private TableView<CaixaTransacaoPagamentoDTO> tabelaCaixa;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, Long> idcaixa;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, Date> data;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, String> descricao;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, Double> valortotal;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, String> descricaotransacao;
    @FXML private TableColumn<CaixaTransacaoPagamentoDTO, String> descricaopagamento;
    
    @FXML private TextField txtTotal;
    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private DatePicker dtData;
    @FXML private ComboBox<TipoTransacaoDTO> cmbTransacao;
    @FXML private ComboBox<TipoPagamentoDTO> cmbPagamento;
    @FXML private Button btSalvarAlterar;
    @FXML private Button btExcluir;
    @FXML private Button btFechar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableCaixa();
        listaDeTransacaoComboBox();
        listaDePagamentoComboBox();
        dtData.setValue(LocalDate.now());
        funcaoTotalizador();
    }    
    
    //Listar itens da Tabela Caixa do dia vigente na tela 
    private void initTableCaixa() {
        idcaixa.setCellValueFactory(new PropertyValueFactory<>("idcaixa"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        valortotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
        descricaotransacao.setCellValueFactory(new PropertyValueFactory<>("descricaotransacao"));
        descricaopagamento.setCellValueFactory(new PropertyValueFactory<>("descricaopagamento"));

        tabelaCaixa.setItems(listaCaixa());
    }
    
    private ObservableList<CaixaTransacaoPagamentoDTO> listaCaixa() {
        try {
            return FXCollections.observableArrayList(cSER.listarTabela(Date.from(Instant.now())));
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Preenche a lista de Tipos de Transação no COMBOBOX Tipos de Transação só trás Descrição
    private ObservableList<TipoTransacaoDTO> listaDeTransacaoComboBox() {
        ObservableList listaDeTransacaoComboBox;
        try {
            listaDeTransacaoComboBox = FXCollections.observableArrayList(ttSER.listarOrdemCombo());
            cmbTransacao.setItems(listaDeTransacaoComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Preenche a lista de Tipos de Pagamento no COMBOBOX Tipos de Pagamento só trás Descrição
    private ObservableList<TipoPagamentoDTO> listaDePagamentoComboBox() {
        ObservableList listaDePagamentoComboBox;
        try {
            listaDePagamentoComboBox = FXCollections.observableArrayList(tpSER.listarOrdemCombo());
            cmbPagamento.setItems(listaDePagamentoComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @FXML
    void aoSelecionarPagamento(ActionEvent event) {
        TipoPagamentoDTO pagamento = cmbPagamento.getSelectionModel().getSelectedItem();
        id_pagamento = pagamento.getIdpagamento();
    }

    @FXML
    void aoSelecionarTransacao(ActionEvent event) {
        TipoTransacaoDTO transacao = cmbTransacao.getSelectionModel().getSelectedItem();
        id_transacao = transacao.getIdtransacao();
    }
    
    @FXML
    void btExcluirAction(ActionEvent event) {
        if(!txtId.getText().trim().equals("")){
            try {
                cSER.excluir(Long.parseLong(txtId.getText()));
                limparTela();
                initTableCaixa();
                funcaoTotalizador();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }else{
            Alerts.showAlert("ATENÇÂO", "", "É necessário selecionar um itens para exclusão", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void btFecharAction(ActionEvent event) {
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

    //Vaerifiva se todos os valores estão preenchidos e grava ou altera os dados
    @FXML
    void btSalvarAlteraAction(ActionEvent event) {
        if(id_pagamento !=0l){
            if(id_transacao != 0l){
                if(dtData.getValue() != null){
                    if(!txtDescricao.getText().trim().equals("")){
                        if(!txtId.getText().trim().equals("")){
                            try {
                                capturaInformacoes();
                                cSER.alterar(cDTO);
                                initTableCaixa();
                                limparTela();
                                funcaoTotalizador();
                            } catch (ApplicationException ex) {
                                ex.printStackTrace();
                            }
                        }else{
                            try {
                                capturaInformacoes();
                                cSER.incluir(cDTO);
                                initTableCaixa();
                                limparTela();
                                funcaoTotalizador();
                            } catch (ApplicationException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }else{
                        Alerts.showAlert("ATENÇÂO", "", "É necessário informar a Descrição do item", Alert.AlertType.WARNING);
                    }
                }else{
                    Alerts.showAlert("ATENÇÂO", "", "É necessário Selecionar uma data", Alert.AlertType.WARNING);
                }
            }else{
                Alerts.showAlert("ATENÇÂO", "", "É necessário Selecionar Transação financeira", Alert.AlertType.WARNING);
            }
        }else{
            Alerts.showAlert("ATENÇÂO", "", "É necessário Selecionar Descrição do Pagamento", Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    void clickedTabelaCaixa(MouseEvent event) {
        if (event.getClickCount() ==2){
            if(tabelaCaixa.getSelectionModel().getSelectedItem()== null){
                Alerts.showAlert("ATENÇÂO", "", "Não existe informações a carregar 'a linha selecionada' se encontra em branco", Alert.AlertType.WARNING);
            }else{
                txtId.setText(tabelaCaixa.getSelectionModel().getSelectedItem().getIdcaixa().toString());
                txtDescricao.setText(tabelaCaixa.getSelectionModel().getSelectedItem().getDescricao());
                txtValor.setText(formata.doubleParaStringComPontoDeMilhar(tabelaCaixa.getSelectionModel().getSelectedItem().getValortotal()));
                dtData.setValue(conversor.asLocalDate(tabelaCaixa.getSelectionModel().getSelectedItem().getData()));
                TipoTransacaoDTO transacao = cmbTransacao.getSelectionModel().getSelectedItem();
                cmbTransacao.setValue(transacao);
                TipoPagamentoDTO pagamento = cmbPagamento.getSelectionModel().getSelectedItem();
                cmbPagamento.setValue(pagamento);
            }
        }
    }
    
    private void capturaInformacoes(){
        if(!txtId.getText().trim().equals("")){
            cDTO.setIdcaixa(Long.parseLong(txtId.getText()));
        }
        cDTO.setData(conversor.asDate(dtData.getValue()));
        cDTO.setDescricao(txtDescricao.getText());
//        if(cmbTransacao.getValue().getDescricao().equals("Entrada")){
            cDTO.setValortotal(Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(txtValor.getText())));
//        }else if(cmbTransacao.getValue().getDescricao().equals("Saida")){
//            cDTO.setValortotal(-1 * Double.parseDouble(formata.convertendoTextoparaDoubleAmericano(txtValor.getText())));
//        }
        cDTO.setIdpagamento(id_pagamento);
        cDTO.setIdtransacao(id_transacao);
    }
    
    private void limparTela(){
        txtId.setText("");
        dtData.setValue(LocalDate.now());
        txtDescricao.setText("");
        txtValor.setText("");
    }
    
    private void funcaoTotalizador(){
        try {
            for (int i = 0; i < cSER.listarTabela(Date.from(Instant.now())).size(); i++) {
//                if(cSER.listarTabela(Date.from(Instant.now())).get(i).getDescricaotransacao().equals("Entrada")){
                    valor += cSER.listarTabela(Date.from(Instant.now())).get(i).getValortotal();
//                }else{
//                    valor -= cSER.listarTabela(Date.from(Instant.now())).get(i).getValortotal();
//                }
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        txtTotal.setText(formata.doubleParaStringComPontoDeMilhar(valor));
        valor = 0.0;
    }
}
