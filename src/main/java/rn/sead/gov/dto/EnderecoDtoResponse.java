package rn.sead.gov.dto;
import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.EnderecoController;
import rn.sead.gov.model.Endereco;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EnderecoDtoResponse extends RepresentationModel<EnderecoDtoResponse> {

    Long numero;

    String bairro;

    String logradouro;

    String cidade;

    String estado;

    String pais;

    public EnderecoDtoResponse(Endereco e) {
        this.numero = e.getNumero();
        this.bairro = e.getBairro();
        this.logradouro = e.getLogradouro();
        this.cidade = e.getCidade();
        this.estado = e.getEstado();
        this.pais = e.getPais();

        add(linkTo(EnderecoController.class).slash(e.getId()).withSelfRel());
        add(linkTo(EnderecoController.class).withRel("AllEnderecos"));

    }

}
