package rn.sead.gov.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import rn.sead.gov.controller.DeficienciaController;
import rn.sead.gov.model.Deficiencia;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class DeficienciaDtoResponse extends RepresentationModel<DeficienciaDtoResponse> {

    String descricao;

    String cid;

    String tipo;

    public DeficienciaDtoResponse(Deficiencia d) {
        this.descricao = d.getDescricao();
        this.cid = d.getCid();
        this.tipo = d.getTipo();

        add(linkTo(DeficienciaController.class).slash(d.getId()).withSelfRel());
        add(linkTo(DeficienciaController.class).withRel("AllDeficiencias"));
    }
}
