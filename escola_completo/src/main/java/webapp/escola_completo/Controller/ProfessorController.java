package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import webapp.escola_completo.Model.Professor;
import webapp.escola_completo.Repository.ProfessorRepository;
import webapp.escola_completo.Repository.VerificaCadProfRepository;



@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository ar;
    @Autowired
    private VerificaCadProfRepository vcar;

    @PostMapping("/cad-professor")
    public String postCadProf(Professor prof) {
        String cpfVerificar = vcar.findByCpf(prof.getCpf()).getCpf();
        if (cpfVerificar.equals(prof.getCpf())) {
            ar.save(prof);
        }

        return "professor/login-professor";


    }

}