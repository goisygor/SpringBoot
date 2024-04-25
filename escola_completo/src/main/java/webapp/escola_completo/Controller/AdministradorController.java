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

    private boolean acessoInternoAdm = false;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private VerificaCadastroAdmRepository verificaCadastroAdmRepository;

    @PostMapping("/cadastro-adm")
    public ModelAndView cadastrarAdministrador(Administrador administrador) {
        ModelAndView modelAndView = new ModelAndView("adm/login-adm");
        
        if (verificaCadastroAdmRepository.existsById(administrador.getCpf())) {
            administradorRepository.save(administrador);
            modelAndView.addObject("msg", "Cadastro realizado com sucesso");
            modelAndView.addObject("classe", "verde");
        } else {
            modelAndView.addObject("msg", "Cadastro não realizado (CPF já cadastrado)");
            modelAndView.addObject("classe", "vermelho");
        }

        return modelAndView;
    }

    @PostMapping("/login-adm")
    public ModelAndView fazerLogin(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/interna-adm");

        Administrador administrador = administradorRepository.findByCpf(cpf);

        if (administrador != null && administrador.getSenha().equals(senha)) {
            modelAndView.addObject("msg", "Login realizado com sucesso");
            modelAndView.addObject("classe", "verde");
            acessoInternoAdm = true;
        } else {
            String mensagem = "Login não efetuado";
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            modelAndView.setViewName("redirect:/login-adm");
        }

        return modelAndView;
    }

    @GetMapping("/interna-adm")
    public ModelAndView acessarPaginaInterna(RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView("adm/interna-adm");

        if (!acessoInternoAdm) {
            String mensagem = "Acesso não permitido - faça login";
            modelAndView.setViewName("redirect:/login-adm");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return modelAndView;
    }

    @PostMapping("/logout-adm")
    public ModelAndView fazerLogout(RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/interna-adm");
        attributes.addFlashAttribute("msg", "Logout efetuado");
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoAdm = false;
        return modelAndView;
    }
}
