package rn.sead.gov.controller;

import rn.sead.gov.controller.generic.AbstractController;
import rn.sead.gov.model.Deficiencia;
import rn.sead.gov.service.DeficienciaService;

public class DeficienciaController extends AbstractController<Deficiencia, DeficienciaService> {

    public DeficienciaController(DeficienciaService service) {
        super(service);
    }
}
