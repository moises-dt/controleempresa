package controller;

import DTO.ClienteFornecedorFuncionarioDTO;
import DTO.PagarReceberClienteParcelamentoDTO;
import DTO.PagarReceberDTO;
import DTO.ParcelamentoDTO;
import DTO.ParcelasPRDTO;
import DTO.TipoContaDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ClienteFornecedorFuncionarioService;
import service.PagarReceberService;
import service.ParcelamentoService;
import service.ParcelasPRService;
import service.TipoContaService;
import util.Calculos;
import util.Conversor;
import util.Formatar;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class PagarReceberController implements Initializable {

    Double valor = 0.0, juros = 0.0;
    Integer qtdparcelas = 0;
    Boolean entrada;
    Date data;
    Long id = 0l;
    
    PagarReceberService pgSER = new PagarReceberService();
    PagarReceberDTO pg = new PagarReceberDTO();
    
    ParcelamentoDTO pc = new ParcelamentoDTO();
    ParcelamentoService pcSER = new ParcelamentoService();
    
    TipoContaDTO tc = new TipoContaDTO();
    TipoContaService tcSER = new TipoContaService();
    
    ParcelasPRDTO prDTO = new ParcelasPRDTO();
    ParcelasPRService prSER = new ParcelasPRService();
    
    ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
    ClienteFornecedorFuncionarioService cffSER = new ClienteFornecedorFuncionarioService();
    
    Calculos calcular = new Calculos();
    Formatar formatar = new Formatar();
    Conversor conversor = new Conversor();
    
    @FXML private Button BtnSalvarAlterar;
    @FXML private Button BtnEcluir;
    @FXML private Button BtnFechar;

    @FXML private DatePicker txtDataCadastro;
    @FXML private TextField txtIdPagar;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtIdCliente;
    @FXML private ComboBox<ClienteFornecedorFuncionarioDTO> cbNomeRazao;
    @FXML private TextField txtValorCompra;
    @FXML private TextField txtJuros;
    @FXML private TextField txtValorParcela;
    @FXML private TextField txtValorFinal;
    @FXML private ComboBox<ParcelamentoDTO> CmbParcelamento;
    @FXML private ComboBox<TipoContaDTO> CmbTipoConta;
    
    @FXML private RadioButton rbPadrao;
    @FXML private RadioButton rbAPagar;
    @FXML private RadioButton rbAReceber;
    @FXML private RadioButton rbTodas;
    
    @FXML private TableView<PagarReceberClienteParcelamentoDTO> tabela;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Long> tblId;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Date> tblData;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, String> tblDescricao;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Integer> tblParcelas;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Double> tblValorParcela;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Double> tblValorCompra;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Double> tblValorFinal;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Double> tblJuros;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, String> tblTipoConta;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, String> tblIdCliente;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, String> tblIdParcelamento;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Long> tblidparcela;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Long> tblidpagarreceber;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, String> tblparcelanumero;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Date> tbldatavencimento;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Boolean> tblpago;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Double> tblvalorpago;
    @FXML private TableColumn<PagarReceberClienteParcelamentoDTO, Date> tbldatapagamento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTablePagarReceber();
        listaParcelamentoComboBox();
        listaTipoContaComboBox();
        listaClienteComboBox();
        txtDataCadastro.setValue(LocalDate.now());
        // Só para teste
        txtValorCompra.setText(formatar.doubleParaStringComPontoDeMilhar(1890.0));
    }
    
    public void initTablePagarReceber() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblData.setCellValueFactory(new PropertyValueFactory<>("data_gerada"));
        tblDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao_pagar"));
        tblParcelas.setCellValueFactory(new PropertyValueFactory<>("numero_parcelas"));
        tblValorParcela.setCellValueFactory(new PropertyValueFactory<>("valor_parcela"));
        tblValorCompra.setCellValueFactory(new PropertyValueFactory<>("valor_final"));
        tblValorFinal.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
        tblJuros.setCellValueFactory(new PropertyValueFactory<>("juros"));
        tblTipoConta.setCellValueFactory(new PropertyValueFactory<>("descricao_conta"));
        tblIdCliente.setCellValueFactory(new PropertyValueFactory<>("nome_razao"));
        tblIdParcelamento.setCellValueFactory(new PropertyValueFactory<>("descricao_parcelamento"));
        tblidparcela.setCellValueFactory(new PropertyValueFactory<>("id_parcela"));
        tblidpagarreceber.setCellValueFactory(new PropertyValueFactory<>("id_pagar_receber"));
        tblparcelanumero.setCellValueFactory(new PropertyValueFactory<>("parcela_numero"));
        tbldatavencimento.setCellValueFactory(new PropertyValueFactory<>("data_vencimento"));
        tblpago.setCellValueFactory(new PropertyValueFactory<>("pago"));
        tblvalorpago.setCellValueFactory(new PropertyValueFactory<>("valor_pago"));
        tbldatapagamento.setCellValueFactory(new PropertyValueFactory<>("data_pagamento"));
        
        if(rbPadrao.isSelected()){
            tabela.setItems(listarOrdemTabelaPadrao());
        }else if(rbAPagar.isSelected()){
            tabela.setItems(listarOrdemTabelaAPagar());
        }else if(rbAReceber.isSelected()){
            tabela.setItems(listarOrdemTabelaAReceber());
        }else if(rbTodas.isSelected()){
            tabela.setItems(listarOrdemTabelaTudo());
        }
    }
    
    //Preenche a tabela de Pagar e Receber   
    private ObservableList<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaPadrao() {
        try {
            return FXCollections.observableArrayList(pgSER.listarOrdemTabelaPadrao());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private ObservableList<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAPagar() {
        try {
            return FXCollections.observableArrayList(pgSER.listarOrdemTabelaAPagar());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private ObservableList<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaAReceber() {
        try {
            return FXCollections.observableArrayList(pgSER.listarOrdemTabelaAReceber());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private ObservableList<PagarReceberClienteParcelamentoDTO> listarOrdemTabelaTudo() {
        try {
            return FXCollections.observableArrayList(pgSER.listarOrdemTabelaTudo());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Preenche a ComboBox de Parcelamento
    private ObservableList<ParcelamentoDTO> listaParcelamentoComboBox() {
        ObservableList listaParcelamentoComboBox;
        try {
            listaParcelamentoComboBox = FXCollections.observableArrayList(pcSER.listarOrdem());
            CmbParcelamento.setItems(listaParcelamentoComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Preenche a ComboBox de TipoConta
    private ObservableList<TipoContaDTO> listaTipoContaComboBox() {
        ObservableList listaTipoContaComboBox;
        try {
            listaTipoContaComboBox = FXCollections.observableArrayList(tcSER.listar());
            CmbTipoConta.setItems(listaTipoContaComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Preenche a ComboBox de Cliente Fornecedor
    private ObservableList<ClienteFornecedorFuncionarioDTO> listaClienteComboBox() {
        ObservableList listaClienteComboBox;
        try {
            listaClienteComboBox = FXCollections.observableArrayList(cffSER.listarOrdem());
            cbNomeRazao.setItems(listaClienteComboBox);
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    //Preenche o camo TXT id ao selecionar o cliente ou fornecedor
    @FXML
    void aoSelecionarFornecedor() {
        ClienteFornecedorFuncionarioDTO fornecedor = cbNomeRazao.getSelectionModel().getSelectedItem();
        txtIdCliente.setText(fornecedor.getId().toString());
    }
    
    @FXML
    void aoSelecionarParcelamento(){
        ParcelamentoDTO parcela = CmbParcelamento.getSelectionModel().getSelectedItem();
        txtJuros.setText(parcela.getTaxajuros().toString());
        calculos();
    }
    
    @FXML
    void onActionAPagar(ActionEvent event) {
        rbAReceber.setSelected(false);
        rbPadrao.setSelected(false);
        rbTodas.setSelected(false);
        rbAPagar.setSelected(true);
        initTablePagarReceber();
    }

    @FXML
    void onActionAReceber(ActionEvent event) {
        rbPadrao.setSelected(false);
        rbTodas.setSelected(false);
        rbAPagar.setSelected(false);
        rbAReceber.setSelected(true);
        initTablePagarReceber();
    }

    @FXML
    void onActionPadrao(ActionEvent event) {
        rbTodas.setSelected(false);
        rbAPagar.setSelected(false);
        rbAReceber.setSelected(false);
        rbPadrao.setSelected(true);
        initTablePagarReceber();
    }

    @FXML
    void onActionTodas(ActionEvent event) {
        rbAPagar.setSelected(false);
        rbAReceber.setSelected(false);
        rbPadrao.setSelected(false);
        rbTodas.setSelected(true);
        initTablePagarReceber();
    }
    
    private void calculos(){
        valor = Double.parseDouble(formatar.convertendoTextoparaDoubleAmericano(txtValorCompra.getText()));
        juros = Double.parseDouble(txtJuros.getText());
        ParcelamentoDTO parcela = CmbParcelamento.getSelectionModel().getSelectedItem();
        qtdparcelas = Integer.parseInt(parcela.getQuantidadeparcelas().toString());
        try {
            data = conversor.localdatetoDate(txtDataCadastro.getValue());
            entrada = pcSER.buscarParcelamentoID(parcela.getId()).getEntrada();
            //Verifica se o campo Entrada está marcado como TRUE, nesse campo fica
            //a informação se o cliente precisa ou não dar entrada no valor
            if(entrada == TRUE){
                calcular.jurosCompostos(valor, juros, qtdparcelas, data);
            }else {
                calcular.jurosCompostos(valor, juros, qtdparcelas, data);
            }
        } catch (ParseException | ApplicationException ex) {
            ex.printStackTrace();
        }
        txtValorParcela.setText(formatar.doubleParaStringComPontoDeMilhar(calcular.jurosCompostosParcelado(valor, juros, qtdparcelas)));
        txtValorFinal.setText(formatar.doubleParaStringComPontoDeMilhar(calcular.jurosCompostosParcelado(valor, juros, qtdparcelas)* qtdparcelas));
    }
    
    @FXML
    public void onFecharAction() {
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
    void aoSalvarAlterar(ActionEvent event) {
        
        try {
            data = conversor.localdatetoDate(txtDataCadastro.getValue());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if(txtIdPagar.getText().trim().equals("")){
            try {
                capturandoInformacoesPagarReceber();
                id = pgSER.incluir(pg);
                txtIdPagar.setText(id.toString());
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
            GregorianCalendar gc = new GregorianCalendar();
            int parc = 1;
            if(entrada == true){
                for (int e = 0; e < qtdparcelas; e++) {
                    gc.setTime(data);
                    gc.add(GregorianCalendar.MONTH, e);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = gc.getTime();
                    capturarInformacoesParcelas(parc, qtdparcelas, d);
                    try {
                        prSER.incluir(prDTO);
                    } catch (ApplicationException ex) {
                        ex.printStackTrace();
                    }
                    parc++;
                }
            }else if(entrada == false){
                for (int e = 1; e <= qtdparcelas; e++) {
                    gc.setTime(data);
                    gc.add(GregorianCalendar.MONTH, e);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = gc.getTime();
                    capturarInformacoesParcelas(parc, qtdparcelas, d);
                    try {
                        prSER.incluir(prDTO);
                    } catch (ApplicationException ex) {
                        ex.printStackTrace();
                    }
                    parc++;
                }
            }
        initTablePagarReceber();
        limparTelaPagar();
        }else{
            try {
                capturandoInformacoesPagarReceber();
                pgSER.alterar(pg);
                initTablePagarReceber();
                limparTelaPagar();
            } catch (ApplicationException ex) {
                ex.printStackTrace();            }
        }
    }
    
    @FXML
    void aoExcluir(ActionEvent event) {
        if(txtIdPagar.getText().trim().equals("")){
            Alerts.showAlert("ATENÇÂO", "", "É necessário selecionar a informação a Excluir", Alert.AlertType.WARNING);
        }else{
            try {
                prSER.excluir(Long.parseLong(txtIdPagar.getText()));
                pgSER.excluir(Long.parseLong(txtIdPagar.getText()));
                initTablePagarReceber();
                limparTelaPagar();
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @FXML
    void aoClicarTabela(MouseEvent event) {
        if(event.getClickCount() == 2){
            limparTelaPagar();
            if(tabela.getSelectionModel().getSelectedItem() == null){
                Alerts.showAlert("ATENÇÂO", "", "É necessário selecionar alguma informação", Alert.AlertType.WARNING);
            }else{
                txtIdPagar.setText(tabela.getSelectionModel().getSelectedItem().getId().toString());
                txtDataCadastro.setValue(conversor.asLocalDate(tabela.getSelectionModel().getSelectedItem().getData_gerada()));
                txtDescricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao_pagar());
                try {
                    txtIdCliente.setText(cffSER.buscarclientefornecedorfuncionario(tabela.getSelectionModel().getSelectedItem().getNome_razao()).getId().toString());
                } catch (ApplicationException ex) {
                    ex.printStackTrace();
                }
                txtValorCompra.setText(formatar.doubleParaStringComPontoDeMilhar(tabela.getSelectionModel().getSelectedItem().getValor_final()));
                txtJuros.setText(tabela.getSelectionModel().getSelectedItem().getJuros().toString());
                txtValorParcela.setText(formatar.doubleParaStringComPontoDeMilhar(tabela.getSelectionModel().getSelectedItem().getValor_parcela()));
                txtValorFinal.setText(formatar.doubleParaStringComPontoDeMilhar(tabela.getSelectionModel().getSelectedItem().getValor_total()));
            }
        }
    }
    
    public void capturandoInformacoesPagarReceber(){
        
        if(txtDescricao.getText().trim().equals("")) {
            Alerts.showAlert("ATENÇÂO", "", "É necessário Descrever o Contas a Pagar ou Receber", Alert.AlertType.WARNING);
        }else{
            if(txtIdCliente.getText().trim().equals("")){
                Alerts.showAlert("ATENÇÂO", "", "É necessário Selecionar o Cliente ou o Fornecedor", Alert.AlertType.WARNING);
            }else{
                if(CmbTipoConta.getValue() == null){
                    Alerts.showAlert("ATENÇÂO", "", "É necessário Selecionar o Tipo da Conta", Alert.AlertType.WARNING);
                }else{
                    Long parcela = Long.parseLong(CmbParcelamento.getSelectionModel().getSelectedItem().getQuantidadeparcelas().toString());
                    Long idparcela = CmbParcelamento.getSelectionModel().getSelectedItem().getId();
                    Long tipoconta = CmbTipoConta.getSelectionModel().getSelectedItem().getIdconta();

                    if(!txtIdPagar.getText().trim().equals("")){
                        pg.setId(Long.parseLong(txtIdPagar.getText()));    
                    }
                    try {
                        pg.setData_gerada(conversor.localdatetoDate(txtDataCadastro.getValue()));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    pg.setDescricao_pagar(txtDescricao.getText());
                    pg.setNumero_parcelas(parcela);
                    pg.setValor_parcela(Double.parseDouble(formatar.convertendoTextoparaDoubleAmericano(txtValorParcela.getText())));
                    pg.setValor_total(Double.parseDouble(formatar.convertendoTextoparaDoubleAmericano(txtValorFinal.getText())));
                    pg.setValor_final(Double.parseDouble(formatar.convertendoTextoparaDoubleAmericano(txtValorCompra.getText())));
                    pg.setJuros(Double.parseDouble(txtJuros.getText()));
                    pg.setId_tipo_conta(tipoconta);
                    pg.setId_cliente(Long.parseLong(txtIdCliente.getText()));
                    pg.setId_parcelamento(idparcela);
                }       
            }
        }
    }

    
    public void capturarInformacoesParcelas(Integer parcelan,  Integer parcelaqtd, Date data){
        prDTO.setId_pagar_receber(Long.parseLong(txtIdPagar.getText()));
        prDTO.setParcela_numero(parcelan +"/"+parcelaqtd);
        prDTO.setData_vencimento(data);
        prDTO.setPago(false);
    }

    public void limparTelaPagar(){
        txtIdPagar.setText("");
        txtDataCadastro.setValue(LocalDate.now());
        txtDescricao.setText("");
        txtValorParcela.setText("");
        txtValorCompra.setText("");
        txtValorFinal.setText("");
        txtJuros.setText("");
        txtIdCliente.setText("");
    }
}
