package rn.sead.gov.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Where(clause = "removed IS NULL")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@CPF(message = "CPF inválido")
	private String cpf;

	@Digits(integer = 20, fraction = 0, message = "RG deve ser um número inteiro")
	@Positive(message = "RG deve ser um número positivo")
	private String rg;

	private String uf;

	private String nacionalidade;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Pattern(regexp = "M|F", message = "M ou F")
	private String sexo;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	private String pai;

	private String mae;

	@Column(name = "nome_social")
	private String nomeSocial;

	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@JsonIgnore
	@OneToOne(mappedBy = "pessoaFisica")
	private Servidor servidor;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pessoa_deficiencia", joinColumns = { @JoinColumn(name = "pessoa_id") }, inverseJoinColumns = {
			@JoinColumn(name = "deficiencia_id") })
	private List<Deficiencia> deficiencias;

	public PessoaFisica(String nome, String email, String cpf, String rg, String nacionalidade,
						String sexo, Date dataNascimento, Endereco endereco) {
		super(nome, email);
		this.cpf = cpf;
		this.rg = rg;
		this.nacionalidade = nacionalidade;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.uf = null;
		this.estadoCivil = null;
		this.pai = null;
		this.mae = null;
		this.nomeSocial = null;
		this.servidor = null;
	}

	public void addDeficiencia(Deficiencia novaDeficiencia) {
		deficiencias.add(novaDeficiencia);
		novaDeficiencia.getPessoasFisicas().add(this);
	}

	public void removeDeficiencia(Deficiencia removeDeficiencia) {
		deficiencias.remove(removeDeficiencia);
		removeDeficiencia.getPessoasFisicas().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		PessoaFisica pessoaFisica = (PessoaFisica) o;
		return getId() != null && Objects.equals(getId(), pessoaFisica.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
