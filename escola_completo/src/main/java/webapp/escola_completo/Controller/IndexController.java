package webapp.escola_completo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView homeIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/login-adm")
    public ModelAndView abrirLoginAdm() {
        ModelAndView mv = new ModelAndView("adm/login-adm");
        return mv;
    }

    @GetMapping("/cad-adm")
    public ModelAndView abrirCadAdm() {
        ModelAndView mv = new ModelAndView("adm/cad-adm");
        return mv;
    }

    @GetMapping("/login-professor")
    public ModelAndView abrirLoginProf() {
        ModelAndView mv = new ModelAndView("professor/login-professor");
        return mv;
    }

    @GetMapping("/cad-professor")
    public ModelAndView abrirCadProf() {
        ModelAndView mv = new ModelAndView("professor/cad-professor");
        return mv;
    }

    @GetMapping("/login-aluno")
    public ModelAndView abrirLoginAluno() {
        ModelAndView mv = new ModelAndView("aluno/login-aluno");
        return mv;
    }

    @GetMapping("/cad-aluno")
    public ModelAndView abrirCadAluno() {
        ModelAndView mv = new ModelAndView("aluno/cad-aluno");
        return mv;
    }

    @GetMapping("/controle-disciplina-professor")
    public ModelAndView abrirControleDisciplinasProfessor() {
        ModelAndView mv = new ModelAndView("professor/controle-disciplina-professor");
        return mv;
    }

    @GetMapping("/controle-disciplina-aluno")
    public ModelAndView abrirControleDisciplinasAluno() {
        ModelAndView mv = new ModelAndView("aluno/controle-disciplina-aluno");
        return mv;
    }
}
