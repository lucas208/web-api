package rn.sead.gov.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.EnderecoController;
import rn.sead.gov.controller.PessoaFisicaController;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.model.PessoaFisica;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class PessoaFisicaDtoResponse extends RepresentationModel<PessoaFisicaDtoResponse> {

    String nome;

    String cpf;

    String nacionalidade;

    String sexo;

    Date dataNascimento;

    Endereco endereco;

    public PessoaFisicaDtoResponse(PessoaFisica p) {
        this.nome = p.getNome();
        this.cpf = p.getCpf();
        this.nacionalidade = p.getNacionalidade();
        this.sexo = p.getSexo();
        this.dataNascimento = p.getDataNascimento();
        this.endereco = p.getEndereco();

        add(linkTo(PessoaFisicaController.class).slash(p.getId()).withSelfRel());
        add(linkTo(PessoaFisicaController.class).withRel("allPessoasFisicas"));

        this.endereco.add(linkTo(EnderecoController.class).slash(endereco.getId()).withSelfRel());
        this.endereco.add(linkTo(EnderecoController.class).withRel("AllEnderecos"));
    }
}
