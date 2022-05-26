package rn.sead.gov.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.minidev.json.annotate.JsonIgnore;
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
@Table(name = "servidores")
@Where(clause = "removed IS NULL")
public class Servidor extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String matricula;

	@Column(name = "dados_bancarios")
	private String dadosBancarios;

	@OneToOne
	@JoinColumn(name = "pessoa_id")
	private PessoaFisica pessoaFisica;

	@JsonIgnore
	@OneToMany(mappedBy = "servidor", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Vinculo> vinculos = new ArrayList<>();

	public Servidor(String matricula, String dadosBancarios, PessoaFisica pessoaFisica) {
		this.matricula = matricula;
		this.dadosBancarios = dadosBancarios;
		this.pessoaFisica = pessoaFisica;
	}

	public void addVinculo(Vinculo novoVinculo) {
		vinculos.add(novoVinculo);
		novoVinculo.setServidor(this);
	}

	public void removeVinculo(Vinculo removeVinculo) {
		vinculos.remove(removeVinculo);
		removeVinculo.setServidor(null);
	}
}
