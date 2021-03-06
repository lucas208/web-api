package rn.sead.gov.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
@Where(clause = "removed IS NULL")
@Table(name = "deficiencias")
public class Deficiencia extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String descricao;

    private String cid;

    private String tipo;

    public Deficiencia(String descricao, String cid, String tipo) {
        this.descricao = descricao;
        this.cid = cid;
        this.tipo = tipo;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "deficiencias")
    private List<PessoaFisica> pessoasFisicas;

    public List<PessoaFisica> getPessoasFisicas() {
        return pessoasFisicas;
    }
}
