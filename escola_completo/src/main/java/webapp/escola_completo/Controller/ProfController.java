package webapp.escola_completo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Repository.ProfRepository;
import webapp.escola_completo.Repository.VerificaCadProfRepository;



@Controller
public class ProfController {
    boolean acessoInternoProf = false;
    @Autowired
    private ProfRepository ar;
    @Autowired
    private VerificaCadProfRepository vcar;

    @PostMapping("/cad-professor")
    public ModelAndView cadastroProfBd(ProfRepository prof) {

        boolean verificaCpf = vcar.existsById(prof.getCpf());

        ModelAndView mv = new ModelAndView("professor/login-professor");

        if (verificaCpf) {
            ar.save(prof);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Cadastro Não Realizado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("acesso-prof")
    public ModelAndView acessProfLogin(@RequestParam String cpf,
            @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-prof");// página interna de acesso
        try {
            // boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
            boolean acessoCPF = ar.existsById(cpf);
            boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());

            if (acessoCPF && acessoSenha) {
                String mensagem = "Login Realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoProf = true;
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "verde");
            } else {
                String mensagem = "Login Não Efetuado";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-adm");
            }

        } catch (Exception e) {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
        }
        return mv;
    }

    @GetMapping("/interna-prof")
    public ModelAndView acessoPageInternaprof(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("prof/interna-prof");
        if (acessoInternoProf) {
            System.out.println("Acesso Permitido");
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-prof");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

}
