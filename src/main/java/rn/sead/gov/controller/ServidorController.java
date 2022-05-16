package rn.sead.gov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.Servidor;
import rn.sead.gov.service.ServidorService;

@RestController
@RequestMapping("/servidor")
public class ServidorController extends AbstractController<Servidor, ServidorService> {

    public ServidorController(ServidorService service) {
        super(service);
    }

}
