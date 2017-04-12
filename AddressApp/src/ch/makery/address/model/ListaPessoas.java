package ch.makery.address.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thati
 */
@XmlRootElement(name="pessoas") //define o nome do elemento base.
public class ListaPessoas {
    
    private List<Pessoa> pessoas;
    
    @XmlElement(name="pessoa") // é um nome opcional nós podemos especificar para o elemento.
    public List<Pessoa> getPessoas(){
        return pessoas;
    }
    
    public void setPessoas(List<Pessoa> pessoas){
        this.pessoas = pessoas;
    }
    
}
