package rn.sead.gov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vinculos")
public class Vinculo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "data_nomeacao")
    private LocalDate dataNomeacao;

    @Column(name = "data_posse")
    private LocalDate dataPosse;

    @Column(name = "data_exercicio")
    private LocalDate dataExercicio;

    private String categoria;

    @Column(name = "regime_juridico")
    private String regimeJuridico;

    private String tipo;

    @Column(name = "unidade_organizacional")
    private String unidadeOrganizacional;

    @ManyToOne
    @JoinColumn(name = "servidor_id")
    private Servidor servidor;

    @ManyToOne
    @JoinColumn(name = "pessoa_juridica_id")
    private PessoaJuridica pessoaJuridica;
}
