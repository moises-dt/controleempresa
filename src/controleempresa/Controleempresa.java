package controleempresa;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author MDT
 */
public class Controleempresa extends Application {

    public static Stage stage;
    private static Scene PrincipalScene;
    private static Scene LoginScene;

    @Override
    public void start(Stage primarystage) throws Exception {
        stage = primarystage;

        try {
            Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/ViewAcesso.fxml"));
            LoginScene = new Scene(fxmlLogin);

            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/view/ViewPrincipal.fxml"));
            PrincipalScene = new Scene(fxmlPrincipal);
            //primarystage.setTitle("Sistema de Controle Empresarial - ACESSO AO SISTEMA");
            primarystage.initStyle(StageStyle.TRANSPARENT);
            primarystage.setScene(LoginScene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeScene(String str) {
        if (str.equals("principal")) {
            stage.setTitle("Sistema de Controle Empresarial");
            stage.setScene(PrincipalScene);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
