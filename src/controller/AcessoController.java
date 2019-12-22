package controller;

import DTO.UsuarioDTO;
import exception.ApplicationException;
import javafx.application.Application;
import view.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UsuarioService;

/**
 * @author MDT
 */
public class AcessoController extends Application {

    private UsuarioDTO u = new UsuarioDTO();
    private UsuarioService uSer = new UsuarioService();

    @FXML private AnchorPane telaLogin;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtSenha;
    @FXML private Button btAcessar;
    @FXML private Button btSair;

    @FXML
    public void onbtAcessarAction() {
        try {
            u = uSer.autenticar(txtUsuario.getText(), txtSenha.getText());
            if (u.getLogin() == null) {
                Alerts.showAlert("ATENÇÂO", "", "Usuario ou senha Inválido", Alert.AlertType.WARNING);
                limpar();
            } else if (u.getSituacao().equals(false)) {
                Alerts.showAlert("ATENÇÂO", "", "Usuario NÂO está ATIVO", Alert.AlertType.WARNING);
                limpar();
            } else {
                controleempresa.Controleempresa.changeScene("principal");
            }
        } catch (ApplicationException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onbtSairAction() {
        System.exit(0);
    }

    @FXML
    public void limpar() {
        txtUsuario.setText("");
        txtSenha.setText("");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
