package rn.sead.gov.dto;

import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.PessoaFisicaController;
import rn.sead.gov.controller.ServidorController;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.model.Servidor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ServidorDtoResponse extends RepresentationModel<ServidorDtoResponse> {

    String matricula;

    String dadosBancarios;

    PessoaFisica pessoaFisica;

    public ServidorDtoResponse(Servidor s) {
        this.matricula = s.getMatricula();
        this.dadosBancarios = s.getDadosBancarios();
        this.pessoaFisica = s.getPessoaFisica();

        add(linkTo(ServidorController.class).slash(s.getId()).withSelfRel());
        add(linkTo(ServidorController.class).withRel("allServidores"));

        this.pessoaFisica.add(linkTo(PessoaFisicaController.class).slash(this.pessoaFisica.getId()).withSelfRel());
        this.pessoaFisica.add(linkTo(PessoaFisicaController.class).withRel("AllPessoasFisicas"));
    }
}
