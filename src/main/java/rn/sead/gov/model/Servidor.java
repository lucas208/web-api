package rn.sead.gov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "servidores")
public class Servidor extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Informar matrícula")
	@Digits(fraction = 0, integer = 7)
	@Size(message = "Informar matricula com 7 dígitos", min = 7, max = 7)
	private String matricula;
	
	@Column(name = "dados_bancarios")
	private String dadosBancarios;
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private PessoaFisica pessoaFisica;
	
	@OneToMany(mappedBy="servidor", fetch = FetchType.LAZY, orphanRemoval=true, cascade = CascadeType.ALL)
	private List<Vinculo> vinculos = new ArrayList<>();
	
	public void addVinculo(Vinculo novoVinculo) {
		vinculos.add(novoVinculo);
		novoVinculo.setServidor(this);
	}
	
	public void removeVinculo(Vinculo removeVinculo) {
		vinculos.remove(removeVinculo);
		removeVinculo.setServidor(null);
	}
}
