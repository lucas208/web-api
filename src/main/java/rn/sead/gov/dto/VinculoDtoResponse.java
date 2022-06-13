package rn.sead.gov.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.PessoaFisicaController;
import rn.sead.gov.controller.PessoaJuridicaController;
import rn.sead.gov.controller.ServidorController;
import rn.sead.gov.controller.VinculoController;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.model.Servidor;
import rn.sead.gov.model.Vinculo;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class VinculoDtoResponse extends RepresentationModel<VinculoDtoResponse> {

    LocalDate dataNomeacao;

    LocalDate dataPosse;

    LocalDate dataExercicio;

    String categoria;

    String regimeJuridico;

    String tipo;

    String unidadeOrganizacional;

    Servidor servidor;

    PessoaJuridica pessoaJuridica;

    public VinculoDtoResponse(Vinculo v) {
        this.dataNomeacao = v.getDataNomeacao();
        this.dataPosse = v.getDataPosse();
        this.dataExercicio = v.getDataExercicio();
        this.categoria = v.getCategoria();
        this.regimeJuridico = v.getRegimeJuridico();
        this.tipo = v.getTipo();
        this.unidadeOrganizacional = v.getUnidadeOrganizacional();
        this.servidor = v.getServidor();
        this.pessoaJuridica = v.getPessoaJuridica();

        add(linkTo(VinculoController.class).slash(v.getId()).withSelfRel());
        add(linkTo(VinculoController.class).withRel("allVinculos"));

        this.servidor.add(linkTo(ServidorController.class).slash(this.pessoaJuridica.getId()).withSelfRel());
        this.servidor.add(linkTo(ServidorController.class).withRel("AllPessoasFisicas"));

        this.pessoaJuridica.add(linkTo(PessoaJuridicaController.class).slash(this.pessoaJuridica.getId()).withSelfRel());
        this.pessoaJuridica.add(linkTo(PessoaJuridicaController.class).withRel("AllPessoasFisicas"));
    }
}
