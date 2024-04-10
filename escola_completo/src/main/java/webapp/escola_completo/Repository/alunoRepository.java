package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.Aluno;


public interface alunoRepository extends CrudRepository<Aluno, String> {
    Aluno findByCpf(String cpf);

    Aluno findBySenha(String senha);

}