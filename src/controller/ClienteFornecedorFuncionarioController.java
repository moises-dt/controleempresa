package controller;

import DTO.ClienteFornecedorFuncionarioDTO;
import static controleempresa.Controleempresa.stage;
import exception.ApplicationException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ClienteFornecedorFuncionarioService;
import util.Conversor;
import view.util.Alerts;

/**
 * FXML Controller class
 * @author MDT
 */
public class ClienteFornecedorFuncionarioController implements Initializable {

    ClienteFornecedorFuncionarioService cffSer = new ClienteFornecedorFuncionarioService();
    ClienteFornecedorFuncionarioDTO cff = new ClienteFornecedorFuncionarioDTO();
    
    Conversor convers = new Conversor();

    @FXML private Button btSalvarAlterar;
    @FXML private Button btExcluir;
    @FXML private Button btFechar;

    @FXML private RadioButton rdCliente;
    @FXML private RadioButton rdFornecedor;
    @FXML private RadioButton rdFuncionario;

    @FXML private TextField txtId;
    @FXML private TextField txtCpfcnpj;
    @FXML private TextField txtSite;
    @FXML private TextField txtNomerazaosocial;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtNumero;
    @FXML private TextField txtComplemento;
    @FXML private TextField txtCidade;
    @FXML private TextField txtBairro;
    @FXML private TextField txtCep;
    @FXML private TextField txtEmail;
    @FXML private TextField txtUf;
    @FXML private TextField txtSexo;
    @FXML private DatePicker txtDatacadastro;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtCelular;
    
    @FXML private Label lblCpf;
    @FXML private Label lblNome;

    @FXML private TableView<ClienteFornecedorFuncionarioDTO> tabela;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Long>id;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cpf_cnpj;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>nome_razao;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>endereco;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Integer>numero;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>complemento;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>bairro;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cidade;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>cep;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>uf;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>sexo;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>email;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>site;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>telefone;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, String>telefone1;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Date>data_cadastro;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>cliente;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>fornecedor;
    @FXML private TableColumn<ClienteFornecedorFuncionarioDTO, Boolean>funcionario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        txtDatacadastro.setValue(LocalDate.now());
    }
    
    public void initTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cpf_cnpj.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
        nome_razao.setCellValueFactory(new PropertyValueFactory<>("nome_razao"));
        endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        complemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        bairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        cep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        uf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        site.setCellValueFactory(new PropertyValueFactory<>("site"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        telefone1.setCellValueFactory(new PropertyValueFactory<>("telefone1"));
        data_cadastro.setCellValueFactory(new PropertyValueFactory<>("data_cadastro"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        fornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        funcionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));

        tabela.setItems(listaCLientes());
    }

    private ObservableList<ClienteFornecedorFuncionarioDTO> listaCLientes() {
        try {
            return FXCollections.observableArrayList(cffSer.listarOrdem());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @FXML
    public void onbtSalvarAlterarAction() {
        try {
            if (txtCpfcnpj.getText().equals("") | txtNomerazaosocial.getText().equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "O campo CPF/CNPJ ou Nome se encontra em branco", Alert.AlertType.WARNING);
            } else if (cffSer.buscarclientefornecedorfuncionario(txtNomerazaosocial.getText()).getNome_razao() != null) {
                capturarInformações();
                cffSer.alterar(cff);
                initTable();
                Alerts.showAlert("ATENÇÂO", "", "Cliente/Fornecedor/Funcionário já foi ALTERADO com SUCESSO", Alert.AlertType.WARNING);
            }else if(txtDatacadastro.getValue() == null){
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar a data", Alert.AlertType.WARNING);
            }else if (txtId.getText().trim().equals("")){
                capturarInformações();
                cffSer.incluir(cff);
                initTable();
                Alerts.showAlert("ATENÇÂO", "", "Cliente/Fornecedor/Funcionário CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        limparTela();
    }

    @FXML
    public void btExcluirAction() {
        try {
         String idexcluir = tabela.getSelectionModel().getSelectedItem().getId().toString();
            if (idexcluir.equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o que deseja excluir", Alert.AlertType.WARNING);
            } else {
                cffSer.excluir(Long.parseLong(idexcluir));
                initTable();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        limparTela();
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
    void aoAcionar(ActionEvent event) {
        rdCliente.setSelected(true);
        if(rdFornecedor.isSelected()){
            rdFornecedor.setSelected(false);
            rdCliente.setSelected(true);
            lblNome.setText("Nome");
            lblCpf.setText("CPF");
            txtSexo.setEditable(true);
            txtSexo.setDisable(false);
        }
    }
    
    @FXML
    void aoClicar(ActionEvent event) {
        rdFornecedor.setSelected(true);
        if(rdFornecedor.isSelected()){
            rdCliente.setSelected(false);
            rdFuncionario.setSelected(false);
            lblNome.setText("Razão Social");
            lblCpf.setText("CNPJ");
            txtSexo.setDisable(true);
        }
    }

    @FXML
    void aoSelecionar(ActionEvent event) {
        rdFuncionario.setSelected(true);
        if(rdFornecedor.isSelected()){
            rdFornecedor.setSelected(false);
            rdFuncionario.setSelected(true);
            lblNome.setText("Nome");
            lblCpf.setText("CPF");
            txtSexo.setEditable(true);
            txtSexo.setDisable(false);
        }
    }
    
    @FXML
    public void onMouseClicked(MouseEvent event){
        if(event.getClickCount() == 2){
            limparTela();
            txtId.setText(tabela.getSelectionModel().getSelectedItem().getId().toString());
            txtCpfcnpj.setText(tabela.getSelectionModel().getSelectedItem().getCpf_cnpj());
            txtSite.setText(tabela.getSelectionModel().getSelectedItem().getSite());
            txtNomerazaosocial.setText(tabela.getSelectionModel().getSelectedItem().getNome_razao());
            txtEndereco.setText(tabela.getSelectionModel().getSelectedItem().getEndereco());
            txtNumero.setText(tabela.getSelectionModel().getSelectedItem().getNumero().toString());
            txtComplemento.setText(tabela.getSelectionModel().getSelectedItem().getComplemento());
            txtCidade.setText(tabela.getSelectionModel().getSelectedItem().getCidade());
            txtBairro.setText(tabela.getSelectionModel().getSelectedItem().getBairro());
            txtCep.setText(tabela.getSelectionModel().getSelectedItem().getCep());
            txtEmail.setText(tabela.getSelectionModel().getSelectedItem().getEmail());
            txtUf.setText(tabela.getSelectionModel().getSelectedItem().getUf());
            if(tabela.getSelectionModel().getSelectedItem().getSexo() == null){
                txtSexo.setDisable(true);
            }else{
                txtSexo.setDisable(false);
                txtSexo.setText(tabela.getSelectionModel().getSelectedItem().getSexo());
            }
            txtDatacadastro.setValue(convers.asLocalDate(tabela.getSelectionModel().getSelectedItem().getData_cadastro()));
            txtTelefone.setText(tabela.getSelectionModel().getSelectedItem().getTelefone());
            txtCelular.setText(tabela.getSelectionModel().getSelectedItem().getTelefone1());
            //Acionamento do Cliente
            if(tabela.getSelectionModel().getSelectedItem().getCliente() == true) {
                rdCliente.setSelected(true);
            } else {
                rdCliente.setSelected(false);
            }
            //Acionamento do Fornecedor
            if (tabela.getSelectionModel().getSelectedItem().getFornecedor() == true) {
                rdFornecedor.setSelected(true);
            } else {
                rdFornecedor.setSelected(false);
            }
            //Acionamento do Funcionário
            if (tabela.getSelectionModel().getSelectedItem().getFuncionario() == true) {
                rdFuncionario.setSelected(true);
            } else {
                rdFuncionario.setSelected(false);
            }
        }
    }

    public void capturarInformações() {
        cff.setCpf_cnpj(txtCpfcnpj.getText());
        cff.setNome_razao(txtNomerazaosocial.getText());
        cff.setEndereco(txtEndereco.getText());
        //Não deixando o Número em Branco como texto 
        if (txtNumero.getText().equals("")){
            cff.setNumero(0);
        }else{
            cff.setNumero(Integer.parseInt(txtNumero.getText()));
        }
        cff.setComplemento(txtComplemento.getText());
        cff.setBairro(txtBairro.getText());
        cff.setCidade(txtCidade.getText());
        cff.setCep(txtCep.getText());
        cff.setUf(txtUf.getText());
        cff.setSexo(txtSexo.getText());
        cff.setEmail(txtEmail.getText());
        cff.setSite(txtSite.getText());
        cff.setTelefone(txtTelefone.getText());
        cff.setTelefone1(txtCelular.getText());
        //Convertendo a data para envio para o Banco de Dados
        try {
            cff.setData_cadastro(convers.localdatetoDate(txtDatacadastro.getValue()));
            } catch (ParseException ex) {
                ex.printStackTrace();
        }
        //Adicionando o True ou False no Banco de Dados        
        if (rdCliente.isSelected()) {
            cff.setCliente(true);
        } else {
            cff.setCliente(false);
        }
        if (rdFornecedor.isSelected()) {
            cff.setFornecedor(true);
        } else {
            cff.setFornecedor(false);
        }
        if (rdFuncionario.isSelected()) {
            cff.setFuncionario(true);
        } else {
            cff.setFuncionario(false);
        }
        //Capturando o ID do Cliente
        if (txtId.getText().trim().equals("")) {
            
        } else {
            cff.setId(Long.parseLong(txtId.getText()));
        }
    }
    public void limparTela(){
        txtId.setText("");
        txtCpfcnpj.setText("");
        txtSite.setText("");
        txtNomerazaosocial.setText("");
        txtEndereco.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtCidade.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtEmail.setText("");
        txtUf.setText("");
        txtSexo.setText("");
        txtDatacadastro.setValue(LocalDate.now());
        txtTelefone.setText("");
        txtCelular.setText("");
        rdCliente.setSelected(false);
        rdFornecedor.setSelected(false);
        rdFuncionario.setSelected(false);
    }
    
}
