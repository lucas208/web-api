package rn.sead.gov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.model.Servidor;
import rn.sead.gov.model.Vinculo;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class VinculoDtoRequest {

    LocalDate dataNomeacao;

    LocalDate dataPosse;

    LocalDate dataExercicio;

    String categoria;

    String regimeJuridico;

    String tipo;

    String unidadeOrganizacional;

    Servidor servidor;

    PessoaJuridica pessoaJuridica;

    public Vinculo convertToDtoVinculo() {
        return  new Vinculo(this.dataNomeacao, this.dataPosse, this.dataExercicio,
                            this.categoria, this.regimeJuridico, this.tipo,
                            this.unidadeOrganizacional, this.servidor, this.pessoaJuridica);
    }


}
