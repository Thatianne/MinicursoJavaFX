/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acessodeusuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private PasswordField pfSenha;
    @FXML
    private CheckBox cbSenha;

    public static String nome="";
    public static String mensagem="";
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {      
        nome=tfUsuario.getText();
        
        if (tfUsuario.getText().equals("admin") && (tfSenha.getText().equals("1234") || pfSenha.getText().equals("1234"))) {
            /*Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Resultado de acesso");
            info.setHeaderText(tfUsuario.getText()+", você está dentro do sistema");
            info.setContentText("Parabéns");
            info.show();*/
            
            mensagem = "Você está dentro do sistema";          

        } else {
            /*Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resultado de acesso");
            alert.setHeaderText("Acesso negado");
            alert.setContentText("Tente novamente");
            alert.show();*/
            mensagem = "Acesso negado";
        }
        
        Parent p = FXMLLoader.load(getClass().getResource("ResultadoAcesso.fxml"));
            Scene cena = new Scene(p);
            Stage s = (Stage) tfSenha.getScene().getWindow();
            s.hide();
            s.setScene(cena);
            s.show();
        
    }

    @FXML
    private void mostrarSenha(ActionEvent event) {

        String senha;

        if (cbSenha.isSelected()) {//mostrar a senha
            senha = pfSenha.getText();
            pfSenha.setVisible(false);
            tfSenha.setVisible(true);
            tfSenha.setText(senha);
        } else {//não mostrar a senha
            senha = tfSenha.getText();
            tfSenha.setVisible(false);
            pfSenha.setVisible(true);
            pfSenha.setText(senha);
        }

        System.out.println(senha);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
