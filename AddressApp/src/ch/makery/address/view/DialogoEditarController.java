package ch.makery.address.view;

import ch.makery.address.model.Pessoa;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Thati
 */
public class DialogoEditarController {

    @FXML
    private TextField primeiroNomeField;
    @FXML
    private TextField ultimoNomeField;
    @FXML
    private TextField ruaField;
    @FXML
    private TextField cepField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField aniversarioField;

    private Stage dialogoStage;
    private Pessoa pessoa;
    private boolean okClicado = false;

    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente após
     * o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    public void setStage(Stage dialogoStage) {
        this.dialogoStage = dialogoStage;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;

        primeiroNomeField.setText(pessoa.getPrimeiroNome());
        ultimoNomeField.setText(pessoa.getUltimoNome());
        ruaField.setText(pessoa.getRua());
        cepField.setText(Integer.toString(pessoa.getCep()));
        cidadeField.setText(pessoa.getCidade());
        aniversarioField.setText(DateUtil.format(pessoa.getAniversario()));
        aniversarioField.setPromptText("dd.mm.yyyy");
    }

    public boolean clicado() {
        return this.okClicado;
    }

    /**
     * Chamado quando o usuário clica OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            pessoa.setPrimeiroNome(primeiroNomeField.getText());
            pessoa.setUltimoNome(ultimoNomeField.getText());
            pessoa.setRua(ruaField.getText());
            pessoa.setCep(Integer.parseInt(cepField.getText()));
            pessoa.setCidade(cidadeField.getText());
            pessoa.setAniversario(DateUtil.parse(aniversarioField.getText()));

            okClicado = true;
            dialogoStage.close();
        }
    }

    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogoStage.close();
    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     *
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (primeiroNomeField.getText() == null || primeiroNomeField.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (ultimoNomeField.getText() == null || ultimoNomeField.getText().length() == 0) {
            errorMessage += "Sobrenome inválido!\n";
        }
        if (ruaField.getText() == null || ruaField.getText().length() == 0) {
            errorMessage += "Rua inválida!\n";
        }

        if (cepField.getText() == null || cepField.getText().length() == 0) {
            errorMessage += "Código Postal inválido!\n";
        } else {
            // tenta converter o código postal em um int.
            try {
                Integer.parseInt(cepField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal inválido (deve ser um inteiro)!\n";
            }
        }

        if (cidadeField.getText() == null || cidadeField.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n";
        }

        if (aniversarioField.getText() == null || aniversarioField.getText().length() == 0) {
            errorMessage += "Aniversário inválido!\n";
        } else {
            if (!DateUtil.validDate(aniversarioField.getText())) {
                errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}
