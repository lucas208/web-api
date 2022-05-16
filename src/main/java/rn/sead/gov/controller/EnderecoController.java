package rn.sead.gov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController extends AbstractController<Endereco, EnderecoService> {

    public EnderecoController(EnderecoService service) {
        super(service);
    }
}
