package controller;

import static controleempresa.Controleempresa.stage;
import DTO.UsuarioDTO;
import exception.ApplicationException;
import view.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.UsuarioService;

/**
 * @author MDT
 */
public class UsuariosController implements Initializable {

    UsuarioService uSer = new UsuarioService();
    UsuarioDTO u = new UsuarioDTO();
    
    @FXML private Button btFecharUsuario;
    @FXML private Button btSalvarAlterarUsuario;
    @FXML private Button btExcluirUsuario;
    
    @FXML private TextField txtLogin;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private CheckBox txtAtivoInativo;
    
    @FXML private TableView<UsuarioDTO> tabela;
    @FXML private TableColumn<UsuarioDTO, String> logincol;
    @FXML private TableColumn<UsuarioDTO, String> senhacol;
    @FXML private TableColumn<UsuarioDTO, String> nomecol;
    @FXML private TableColumn<UsuarioDTO, String> emailcol;
    @FXML private TableColumn<UsuarioDTO, Boolean> situacao;

    //entrada e saida de dados da tabela Usuarios
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTable() {
        logincol.setCellValueFactory(new PropertyValueFactory<>("Login"));
        senhacol.setCellValueFactory(new PropertyValueFactory<>("Senha"));
        nomecol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        situacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        
        //situacao.setCellFactory(CheckBoxTableCell.forTableColumn(situacao));
    
        //javafx.scene.control.cell.CheckBoxTableCell<S,T>;
        
        tabela.setItems(listaDeUsuarios());
    }

    private ObservableList<UsuarioDTO> listaDeUsuarios() {
        try {
            return FXCollections.observableArrayList(uSer.listar());
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void onFecharUsuarioAction() {
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
    public void onbtSalvarAlterarUsuarioAction() {
        capturarInformações();
        try {
            if(txtSenha.getText().equals("")){
                Alerts.showAlert("ATENÇÂO", "", "O campo SENHA se encontra em branco", Alert.AlertType.WARNING);
            }else if (uSer.buscarLogin(txtLogin.getText()).getLogin() != null) {
                uSer.alterar(u);
                LimpaTela();
                Alerts.showAlert("ATENÇÂO", "", "Usuário já existe ALTERADO com SUCESSO", Alert.AlertType.WARNING);
            } else {
                uSer.incluir(u);
                LimpaTela();
                Alerts.showAlert("ATENÇÂO", "", "Usuário CADASTRADO com SUCESSO", Alert.AlertType.WARNING);
            }
            initTable();
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void btExcluirAction() {
        try {
            String login = tabela.getSelectionModel().getSelectedItem().getLogin();
            if (login.equals("")) {
                Alerts.showAlert("ATENÇÂO", "", "Necessário selecionar o que deseja excluir", Alert.AlertType.WARNING);
            } else {
                uSer.excluir(login);
                LimpaTela();
                initTable();
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }

    public void onMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            txtLogin.setText(tabela.getSelectionModel().getSelectedItem().getLogin());
            txtNome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            txtEmail.setText(tabela.getSelectionModel().getSelectedItem().getEmail());
            if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getSituacao(), true )) {
                txtAtivoInativo.setSelected(true);
            } else if (Objects.equals(tabela.getSelectionModel().getSelectedItem().getSituacao(), false)) {
                txtAtivoInativo.setSelected(false);
            }
        }
    }

    public void capturarInformações() {
        u.setLogin(txtLogin.getText());
        u.setSenha(txtSenha.getText());
        u.setNome(txtNome.getText());
        u.setEmail(txtEmail.getText());
        if (txtAtivoInativo.isSelected()) {
            u.setSituacao(true);
        } else {
            u.setSituacao(false);
        }
    }
    
    public void LimpaTela(){
        txtLogin.setText("");
        txtSenha.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtAtivoInativo.setSelected(false);
    }

}
