package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import webapp.connection.Email;

@Controller
public class IndexController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        Email obj = new Email();
        String mensagem = "Olá, seja bem-vinda(o)!";
        mv.addObject("msg", mensagem);
        obj.criaTabela();
        return mv;
    }
}