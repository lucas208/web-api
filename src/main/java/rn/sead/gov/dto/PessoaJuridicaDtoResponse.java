package rn.sead.gov.dto;

import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.EnderecoController;
import rn.sead.gov.controller.PessoaJuridicaController;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.model.PessoaJuridica;

import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PessoaJuridicaDtoResponse extends RepresentationModel<PessoaJuridicaDtoResponse> {

    String cnpj;

    String razaoSocial;

    String nomeFantasia;

    Date dataRegistro;

    String naturezaJuridica;

    String contato;

    Endereco endereco;

    public PessoaJuridicaDtoResponse(PessoaJuridica p) {
        this.cnpj = p.getCnpj();
        this.razaoSocial = p.getRazaoSocial();
        this.nomeFantasia = p.getNomeFantasia();
        this.dataRegistro = p.getDataRegistro();
        this.naturezaJuridica = p.getNaturezaJuridica();
        this.contato = p.getContato();
        this.endereco = p.getEndereco();

        add(linkTo(PessoaJuridicaController.class).slash(p.getId()).withSelfRel());
        add(linkTo(PessoaJuridicaController.class).withRel("allPessoasFisicas"));

        this.endereco.add(linkTo(EnderecoController.class).slash(endereco.getId()).withSelfRel());
        this.endereco.add(linkTo(EnderecoController.class).withRel("AllEnderecos"));
    }

}
