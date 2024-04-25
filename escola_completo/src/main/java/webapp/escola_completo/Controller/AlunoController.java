package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Aluno;
import webapp.escola_completo.Repository.AlunoRepository;
import webapp.escola_completo.Repository.VerificaCadastroAlunoRepository;

@Controller
public class AlunoController {

    boolean acessoInternoAluno = false;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private VerificaCadastroAlunoRepository verificaCadastroAlunoRepository;

    @PostMapping("/cadastro-aluno")
    public ModelAndView cadastroAlunoBd(Aluno aluno) {
        boolean verificaCpf = verificaCadastroAlunoRepository.existsById(aluno.getCpf());
        ModelAndView mv = new ModelAndView("aluno/login-aluno");

        if (verificaCpf) {
            alunoRepository.save(aluno);
            String mensagem = "Cadastro realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Cadastro não realizado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("/acesso-aluno")
    public ModelAndView acessoAlunoLogin(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-aluno");

        try {
            boolean acessoCpf = alunoRepository.existsById(cpf);
            Aluno aluno = alunoRepository.findByCpf(cpf);

            if (acessoCpf && aluno.getSenha().equals(senha)) {
                String mensagem = "Login realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoAluno = true;
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "verde");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            String mensagem = "Login não efetuado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-aluno");
        }

        return mv;
    }

    @GetMapping("/interna-aluno")
    public ModelAndView acessoPageInternaAluno(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("aluno/interna-aluno");

        if (!acessoInternoAluno) {
            String mensagem = "Acesso não permitido - faça login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-aluno");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }
}
