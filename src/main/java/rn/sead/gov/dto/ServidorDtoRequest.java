package rn.sead.gov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.model.Servidor;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ServidorDtoRequest {

    String matricula;

    String dadosBancarios;

    PessoaFisica pessoaFisica;

    public Servidor convertToDtoServidor() {
        return  new Servidor(this.matricula, this.dadosBancarios, this.pessoaFisica);
    }
}
