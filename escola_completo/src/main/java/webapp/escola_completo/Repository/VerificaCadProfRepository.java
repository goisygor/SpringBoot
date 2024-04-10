package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.VerificaCadastroAdm;



public interface VerificaCadProfRepository extends CrudRepository<VerificaCadastroAdm, String> {
    VerificaCadastroAdm findByCpf(String cpf);

    VerificaCadastroAdm findByNome(String nome);
}