package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Pessoa;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Thati
 */
public class PersonOverviewController {

    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, String> primeiroNomeColuna;
    @FXML
    private TableColumn<Pessoa, String> ultimoNomeColuna;

    @FXML
    private Label primeiroNomeLabel;
    @FXML
    private Label ultimoNomeLabel;
    @FXML
    private Label ruaLabel;
    @FXML
    private Label cepLabel;
    @FXML
    private Label cidadeLabel;
    @FXML
    private Label aniversarioLabel;

    private MainApp mainApp;

    public PersonOverviewController() {

    }

    @FXML
    private void initialize() {
        //Inicializa a tabela de pessoas com o primeiro e último nome
        primeiroNomeColuna.setCellValueFactory(cellData -> cellData.getValue().primeiroNome());
        ultimoNomeColuna.setCellValueFactory(cellData -> cellData.getValue().ultimoNome());

        //Limpa os detalhes da pessoa
        mostrarDetalhesPessoais(null);

        //Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança
        tabelaPessoa.getSelectionModel().selectedItemProperty().addListener( //******** VER MAIS SOBRE ISSO DEPOIS ***********
                (observable, oldValue, newValue) -> mostrarDetalhesPessoais(newValue)
        );
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        //adiciona os dados da observable list na tabela
        tabelaPessoa.setItems(mainApp.getPessoasDado());
    }

    private void mostrarDetalhesPessoais(Pessoa pessoa) {
        if (pessoa != null) {
            this.primeiroNomeLabel.setText(pessoa.getPrimeiroNome());
            this.ultimoNomeLabel.setText(pessoa.getUltimoNome());
            this.ruaLabel.setText(pessoa.getRua());
            this.cepLabel.setText(Integer.toString(pessoa.getCep()));
            this.cidadeLabel.setText(pessoa.getCidade());
            this.aniversarioLabel.setText(DateUtil.format(pessoa.getAniversario()));
        } else {
            this.primeiroNomeLabel.setText("");
            this.ultimoNomeLabel.setText("");
            this.ruaLabel.setText("");
            this.cepLabel.setText("");
            this.cidadeLabel.setText("");
            this.aniversarioLabel.setText("");
        }
    }

    @FXML
    private void handleDeletarPessoa() {

        int indexSelecionado = tabelaPessoa.getSelectionModel().getSelectedIndex();

        if (indexSelecionado < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma pessoa selecionada");
            alert.setContentText("Selecione um cadastro");
            alert.showAndWait();
        } else {
            tabelaPessoa.getItems().remove(indexSelecionado);
        }
    }

    /**
     * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
     * detalhes da nova pessoa.
     */
    @FXML
    private void handleNewPerson() {
        Pessoa tempPessoa = new Pessoa();
        boolean okClicked = mainApp.showPersonEditDialog(tempPessoa);
        if (okClicked) {
            mainApp.getPessoasDado().add(tempPessoa);
        }
    }

    /**
     * Chamado quando o usuário clica no botão edit. Abre a janela para editar
     * detalhes da pessoa selecionada.
     */
    @FXML
    private void handleEditPerson() {
        Pessoa pessoaSelecionada = tabelaPessoa.getSelectionModel().getSelectedItem();
        if (pessoaSelecionada != null) {
            boolean okClicked = mainApp.showPersonEditDialog(pessoaSelecionada);
            if (okClicked) {
                mostrarDetalhesPessoais(pessoaSelecionada);
            }

        } else {
            // Nada seleciondo.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Pessoa Selecionada");
            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
            alert.showAndWait();
        }
    }
}
