package rn.sead.gov.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "removed = null")
public class Endereco extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private Long numero;

    private String bairro;

    private String logradouro;

    private String cidade;

    private String estado;

    private String pais;

    @OneToOne(mappedBy = "endereco")
    private PessoaFisica pessoaFisica;

    @OneToOne(mappedBy = "endereco")
    private PessoaJuridica pessoaJuridica;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return getId() != null && Objects.equals(getId(), endereco.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
