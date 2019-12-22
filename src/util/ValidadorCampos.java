package util;

import javafx.scene.control.TextField;

/**
 * @author MDiasT
 */
public class ValidadorCampos {
    
    //Verifica se o que esta sendo digitado é número e inteiro
    public static void setTextFieldInteger(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) ->{
            if(newValue != null && !newValue.matches("\\d*")){
                txt.setText(oldValue);
            }
        });
    }
    
    //Verifica o que está sendo digitado é texto
    public static void setTextFieldString(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) ->{
            if(newValue != null && !newValue.matches("\\D*")){
                txt.setText(oldValue);
            }
        });
    }
    
    public static void setTextFieldMaxLength(TextField txt, int max){
        txt.textProperty().addListener((obs, oldValue, newValue) ->{
            if(newValue != null && newValue.length() >max){
                txt.setText(oldValue);
            }
        });
    }
    
    public static void setTextFieldDouble (TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.matches("\\d*([\\.]\\d*)?")){
                txt.setText(oldValue);
            }
        });
    }
    
    //
    public static void eventFocusLost (TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.matches("\\d*([\\.]\\d*)?")){
                txt.setText(oldValue);
            }
        });
    }
    
   
}
