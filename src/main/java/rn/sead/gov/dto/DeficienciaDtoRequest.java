package rn.sead.gov.dto;

import lombok.*;
import rn.sead.gov.model.Deficiencia;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class DeficienciaDtoRequest {

    String descricao;

    String cid;

    String tipo;

    public Deficiencia convertToDeficiencia(){
        return new Deficiencia(this.descricao, this.cid, this.tipo);
    }
}
