package webapp.escola_completo.Repository;



import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.Disciplina;

import java.util.List;


public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    List<Disciplina> findByNome(String nome);
}