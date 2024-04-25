package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Prof;
import webapp.escola_completo.Repository.ProfRepository;
import webapp.escola_completo.Repository.VerificaCadProfRepository;

@Controller
public class ProfController {
    boolean acessoInternoProf = false;

    @Autowired
    private ProfRepository profRepository;

    @Autowired
    private VerificaCadProfRepository verificaCadProfRepository;

    @PostMapping("/cad-professor")
    public ModelAndView cadastroProfBd(Prof prof) {
        boolean verificaCpf = verificaCadProfRepository.existsById(prof.getCpf());
        ModelAndView mv = new ModelAndView("professor/login-professor");

        if (verificaCpf) {
            profRepository.save(prof);
            String mensagem = "Cadastro realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Cadastro não realizado (CPF já cadastrado)";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("/acesso-professor")
    public ModelAndView acessoProfLogin(@RequestParam String cpf, @RequestParam String senha, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-professor");

        try {
            boolean acessoCPF = profRepository.existsById(cpf);
            boolean acessoSenha = senha.equals(profRepository.findByCpf(cpf).getSenha());

            if (acessoCPF && acessoSenha) {
                String mensagem = "Login realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoProf = true;
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
            mv.setViewName("redirect:/login-adm");
        }

        return mv;
    }

    @GetMapping("/interna-professor")
    public ModelAndView acessoPageInternaProf(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("prof/interna-professor");

        if (!acessoInternoProf) {
            String mensagem = "Acesso não permitido - faça login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-professor");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }
}
