package rn.sead.gov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.Vinculo;
import rn.sead.gov.service.VinculoService;

@RestController
@RequestMapping("/vinculo")
public class VinculoController extends AbstractController<Vinculo, VinculoService> {

    public VinculoController(VinculoService service) {
        super(service);
    }
}
