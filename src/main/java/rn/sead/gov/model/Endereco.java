package rn.sead.gov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "removed = null")
public class Endereco extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Digits(integer = 10, fraction = 0, message = "Número deve ser um número inteiro")
    @Positive(message = "Número deve ser um número positivo")
    private Long numero;

    private String bairro;

    private String logradouro;

    private String cidade;

    private String estado;

    private String pais;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco")
    private PessoaFisica pessoaFisica;

    @JsonIgnore
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
