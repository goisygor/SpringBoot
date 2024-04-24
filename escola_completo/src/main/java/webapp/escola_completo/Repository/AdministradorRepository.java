package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.escola_completo.Model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, String> {
    Administrador findByCpf(String cpf);
    Administrador findByEmail(String email);
    Administrador findBySenha(String senha);
}
