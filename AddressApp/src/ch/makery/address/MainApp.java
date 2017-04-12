package ch.makery.address;

import ch.makery.address.model.ListaPessoas;
import ch.makery.address.model.Pessoa;
import ch.makery.address.view.DialogoEditarController;
import ch.makery.address.view.PersonOverviewController;
import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Thati
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Pessoa> pessoasDado = FXCollections.observableArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {

        //adicionar pessoas à lista
        pessoasDado.add(new Pessoa("Thatianne", "Carvalho"));
        pessoasDado.add(new Pessoa("Hans", "Muster"));
        pessoasDado.add(new Pessoa("Ruth", "Mueller"));
        pessoasDado.add(new Pessoa("Heinz", "Kurz"));
        pessoasDado.add(new Pessoa("Cornelia", "Meier"));
        pessoasDado.add(new Pessoa("Werner", "Meyer"));
        pessoasDado.add(new Pessoa("Lydia", "Kunz"));
        pessoasDado.add(new Pessoa("Anna", "Best"));
        pessoasDado.add(new Pessoa("Stefan", "Meier"));
        pessoasDado.add(new Pessoa("Martin", "Mueller"));
    }

    public ObservableList<Pessoa> getPessoasDado() {
        return this.pessoasDado;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Aplicação para endereços");

        initRootLayout();

        showPersonOverview();
    }

    private void initRootLayout() {
        try {

            //carrega o layout do arquivo fxml
            rootLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml")); //pode atribuir ao um objeto Parent

            //mostra cena contendo o root layout
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showPersonOverview() {
        try {

            //carrega personOverview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();//chama o initialize

            //Define personOverview no centro de rootLayout
            this.rootLayout.setCenter(personOverview);

            //Dá ao controlador acesso à the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre uma janela para editar detalhes para a pessoa especificada. Se o
     * usuário clicar OK, as mudanças são salvasno objeto pessoa fornecido e
     * retorna true.
     *
     * @param pessoa O objeto pessoa a ser editado
     * @return true Se o usuário clicou OK, caso contrário false.
     */
    public boolean showPersonEditDialog(Pessoa pessoa) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/DialogoEditar.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Edit Person");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogoStage.setScene(scene);

            // Define a pessoa no controller.
            DialogoEditarController controller = loader.getController();
            controller.setStage(dialogoStage);
            controller.setPessoa(pessoa);

            // Mostra a janela e espera até o usuário fechar.
            dialogoStage.showAndWait();

            return controller.clicado();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
