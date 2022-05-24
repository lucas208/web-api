package rn.sead.gov.model;

import lombok.*;
import org.hibernate.Hibernate;
import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Pessoa extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @Email(message = "E-mail inválido")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pessoa pessoa = (Pessoa) o;
        return getId() != null && Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
