package rn.sead.gov.model;

import java.util.ArrayList;
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

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String rg;

	private String uf;

	private String nascionalidade;

	@Column(name = "estado_civil")
	private String estadoCivil;

	private char sexo;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	private String pai;

	private String mae;

	@Column(name = "nome_social")
	private String nomeSocial;

	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToOne(mappedBy = "pessoaFisica")
	private Servidor servidor;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pessoa_deficiencia", 
		joinColumns = { @JoinColumn(name = "pessoa_id", referencedColumnName = "id_pessoa") }, 
		inverseJoinColumns = {@JoinColumn(name = "deficiencia_id") })
	private List<Deficiencia> deficiencias = new ArrayList<Deficiencia>();
	
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
