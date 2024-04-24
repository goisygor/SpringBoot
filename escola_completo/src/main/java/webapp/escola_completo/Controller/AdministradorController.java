package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Repository.AdministradorRepository;
import webapp.escola_completo.Repository.VerificaCadastroAdmRepository;

@Controller
public class AdministradorController {

    boolean acessoInternoAdm = false;

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private VerificaCadastroAdmRepository vcar;

    @PostMapping("/cad-adm")
    public ModelAndView cadastroAdmBD(Administrador adm) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        ModelAndView mv = new ModelAndView("adm/login-adm");

        if (verificaCpf) {
            ar.save(adm);
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

    @PostMapping("/login-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");

        try {
            boolean acessoCPF = ar.existsById(cpf);
            Administrador admin = ar.findByCpf(cpf);

            if (acessoCPF && admin.getSenha().equals(senha)) {
                String mensagem = "Login realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoAdm = true;
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

    @GetMapping("/interna-adm")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("adm/interna-adm");

        if (!acessoInternoAdm) {
            String mensagem = "Acesso não permitido - faça login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-adm");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("/logout-adm")
    public ModelAndView logoutAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");
        attributes.addFlashAttribute("msg", "Logout efetuado");
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoAdm = false;
        return mv;
    }
}
