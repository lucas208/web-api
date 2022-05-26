package rn.sead.gov.dto;

import lombok.*;
import rn.sead.gov.model.Endereco;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class EnderecoDtoRequest {

    Long numero;

    String bairro;

    String logradouro;

    String cidade;

    String estado;

    String pais;

    public Endereco convertToEndereco() {
        return new Endereco(this.numero, this.bairro, this.logradouro, this.cidade, this.estado, this.pais);
    }

}
