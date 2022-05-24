package rn.sead.gov.dto;

import lombok.*;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.model.PessoaFisica;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class PessoaFisicaDtoRequest {

    String nome;

    String cpf;

    String nacionalidade;

    String sexo;

    Date dataNascimento;

    Endereco endereco;

    public PessoaFisica convertToPessoaFisica() {
        return new PessoaFisica(this.nome, this.cpf, this.nacionalidade, this.sexo, this.dataNascimento, this.endereco);
    }


}
