package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.escola_completo.Model.Aluno;
import webapp.escola_completo.Repository.VerificaCadastroAlunoRepository;
import webapp.escola_completo.Repository.AlunoRepository;





@Controller
public class AlunoController {
    boolean acessoInternoAluno= false; 
    @Autowired
    private AlunoRepository ar;
    @Autowired
    private VerificaCadastroAlunoRepository  vcar;

    @PostMapping("/cad-aluno")
    public String postCadAluno(Aluno aluno) {
        String cpfVerificar = vcar.findByCpf(aluno.getCpf()).getCpf();
        if (cpfVerificar.equals(aluno.getCpf())) {
            ar.save(aluno);
        }

        return "aluno/login-aluno";
    }
    @PostMapping("/acesso-aluno")
    public ModelAndView acessoAlunoLogin(@RequestParam String cpf, @RequestParam String senha) {
        ModelAndView mv = new ModelAndView();// página interna de acesso
    
        Aluno aluno = ar.findByCpf(cpf);
        if (aluno != null) {
            boolean acessoCPF = cpf.equals(aluno.getCpf());
            boolean acessoSenha = senha.equals(aluno.getSenha());
            if (acessoCPF && acessoSenha) {
                String mensagem = "Login Realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoAluno = true;
                mv.setViewName("redirect:/interna-aluno");
            } else {
                String mensagem = "Login Não Efetuado";
                System.out.println(mensagem);
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "vermelho");
                mv.setViewName("aluno/login-aluno");
            }
        } else {
            String mensagem = "Aluno não encontrado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("aluno/login-aluno");
        }
        return mv;
    }
    
    @GetMapping("/interna-aluno")
    public String acessoPageInternaAluno() {
        ModelAndView mv = new ModelAndView();
        String acesso = "";
        if (acessoInternoAluno) {
            acesso = "aluno/interna-aluno";
        } else {
            acesso = "aluno/login-aluno";
            mv.addObject("classe", "vermelho");
        }

        return acesso;
    }
}