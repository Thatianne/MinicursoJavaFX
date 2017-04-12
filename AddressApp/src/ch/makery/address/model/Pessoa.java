package ch.makery.address.model;

import java.time.LocalDate;
import java.time.Month;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Thati
 */
public class Pessoa {

    /* Com JavaFX é comum o uso de Properties (propriedades) para todos os campos de uma classe model. 
    Uma Property (propriedade) nos permite ser notificado automaticamente caso uma variável seja mudada. 
    Isso nos ajuda a manter a view sincronizada com os dados.
     */
    private final StringProperty primeiroNome;
    private final StringProperty ultimoNome;
    private final StringProperty rua;
    private final IntegerProperty cep;
    private final StringProperty cidade;
    private final ObjectProperty<LocalDate> aniversario;

    public Pessoa() {
        this(null, null);
    }

    public Pessoa(String primeiroNome, String ultimoNome) {
        this.primeiroNome = new SimpleStringProperty(primeiroNome);
        this.ultimoNome = new SimpleStringProperty(ultimoNome);

        //para teste
        this.rua = new SimpleStringProperty("Maria Quitéria");
        this.cep = new SimpleIntegerProperty(123456);
        this.cidade = new SimpleStringProperty("Feira de Santana");
        this.aniversario = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017, 3, 8));
    }

    public String getPrimeiroNome() {
        return primeiroNome.get();
    }

    public StringProperty primeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome.set(primeiroNome);
    }

    public String getUltimoNome() {
        return ultimoNome.get();
    }

    public StringProperty ultimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome.set(ultimoNome);
    }

    public String getRua() {
        return rua.get();
    }

    public StringProperty rua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }

    public Integer getCep() {
        return cep.get();
    }

    public IntegerProperty cep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep.set(cep);
    }

    public String getCidade() {
        return cidade.get();
    }

    public StringProperty cidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade.set(cidade);
    }

    public LocalDate getAniversario() {
        return this.aniversario.get();
    }

    public ObjectProperty<LocalDate> Aniversario() {
        return aniversario;
    }

    public void setAniversario(LocalDate aniversario) {
        this.aniversario.set(aniversario);
    }

}
