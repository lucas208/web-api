package rn.sead.gov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.model.PessoaJuridica;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PessoaJuridicaDtoRequest {

    String cnpj;

    String razaoSocial;

    String nomeFantasia;

    Date dataRegistro;

    String naturezaJuridica;

    String contato;

    Endereco endereco;

    public PessoaJuridica convertToPessoaJuridica() {
        return new PessoaJuridica(this.cnpj, this.razaoSocial, this.nomeFantasia, this.dataRegistro,
                                    this.naturezaJuridica, this.contato);
    }

}
