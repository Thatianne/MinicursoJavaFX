/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acessodeusuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Thati
 */
public class ResultadoAcessoController implements Initializable {
    
    private String nome;
    private String mensagem;
    
    @FXML
    private Label lbnome;
    @FXML
    private Label lbMensagem;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.nome = FXMLDocumentController.nome;
        this.mensagem = FXMLDocumentController.mensagem;
        
        lbnome.setText(nome);
        lbMensagem.setText(mensagem);
        
        
    }    
    
}
