package webapp.escola_completo.Model;



import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VerificaCadastroAdm implements Serializable {
    // Atributos
    @Id 
    private String cpf;
    private String nome;

    // Construtor padrão vazio
    public VerificaCadastroAdm() {
    }

    // Métodos acessores e modificadores
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    } 
}
