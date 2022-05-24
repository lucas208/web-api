package rn.sead.gov.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rn.sead.gov.model.generic.AbstractEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "removed = null")
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
