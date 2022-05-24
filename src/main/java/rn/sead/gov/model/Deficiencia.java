package rn.sead.gov.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name = "deficiencias")
public class Deficiencia extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String cid;
	
	private String tipo;
	
	@ManyToMany(mappedBy="deficiencias")
	private List<PessoaFisica> pessoasFisicas;

	public List<PessoaFisica> getPessoasFisicas(){
		return pessoasFisicas;
	}
}
