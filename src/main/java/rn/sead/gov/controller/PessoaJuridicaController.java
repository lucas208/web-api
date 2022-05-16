package rn.sead.gov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController extends AbstractController<PessoaJuridica, PessoaJuridicaService> {

    public PessoaJuridicaController(PessoaJuridicaService service) {
        super(service);
    }
}
