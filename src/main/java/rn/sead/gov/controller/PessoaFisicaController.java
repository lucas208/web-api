package rn.sead.gov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController extends AbstractController<PessoaFisica, PessoaFisicaService> {

    public PessoaFisicaController(PessoaFisicaService service) {
        super(service);
    }
}
