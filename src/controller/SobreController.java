package controller;

import static controleempresa.Controleempresa.stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * @author MDiasT
 */
public class SobreController {
     
    
    @FXML private AnchorPane sobresistema;
    @FXML private Button btfechar;

     @FXML
    void onBtFecharAction(ActionEvent event) {
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
    
}
