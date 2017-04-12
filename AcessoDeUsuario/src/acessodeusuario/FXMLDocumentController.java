/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acessodeusuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Thati
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfSenha;
    @FXML
    private Button button;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(tfUsuario.getText().equals("admin") && tfSenha.getText().equals("1234")){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Resultado de acesso");
            info.setHeaderText(tfUsuario.getText()+", você está dentro do sistema");
            info.setContentText("Parabéns");
            info.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resultado de acesso");
            alert.setHeaderText("Acesso negado");
            alert.setContentText("Tente novamente");
            alert.show();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
