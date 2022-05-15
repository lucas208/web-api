package rn.sead.gov.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class PessoaFisica extends Pessoa {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PessoaFisica pessoaFisica = (PessoaFisica) o;
        return getId() != null && Objects.equals(getId(), pessoaFisica.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
