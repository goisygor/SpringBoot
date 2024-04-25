package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import webapp.escola_completo.Model.Disciplina;
import webapp.escola_completo.Model.Prof;
import webapp.escola_completo.Repository.DisciplinaRepository;
import webapp.escola_completo.Repository.ProfRepository;
import java.util.List;

@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfRepository professorRepository;

    @GetMapping("/disciplina")
    public ModelAndView listarDisciplinas() {
        ModelAndView modelAndView = new ModelAndView("listar-disciplinas");
        Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }

    @GetMapping("/disciplinas/cadastrar")
    public ModelAndView exibirFormularioCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro-disciplina");
        List<Prof> professores = (List<Prof>) professorRepository.findAll();
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("disciplina", new Disciplina());
        return modelAndView;
    }

    @PostMapping("/disciplina/cadastrar")
    public String cadastrarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplina";
    }
}
