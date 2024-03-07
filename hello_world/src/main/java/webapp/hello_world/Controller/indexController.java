package webapp.hello_world.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** @author Rolfi Luz - Senai * */
@Controller
public class indexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");

        String mensagem = "Olá, seja bem-vinda(o)!";
        mv.addObject("msg", mensagem);

        return mv;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("nome") String nome,
                                    @RequestParam("email") String email,
                                    @RequestParam("telefone") String telefone) {
        ModelAndView mv = new ModelAndView("index");

        String mensagem = "Resultado da Busca!";
        mv.addObject("msg", mensagem);
        mv.addObject("nome", nome);
        mv.addObject("email", email);
        mv.addObject("telefone", telefone);

        return mv;
    }
}