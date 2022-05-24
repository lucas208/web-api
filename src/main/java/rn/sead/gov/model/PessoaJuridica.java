package rn.sead.gov.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Where(clause = "removed = null")
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "data_registro")
    private Date dataRegistro;

    @Column(name = "natureza_juridica")
    private String naturezaJuridica;

    private String contato;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoaJuridica")
    private List<Vinculo> vinculos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PessoaJuridica pessoaJuridica = (PessoaJuridica) o;
        return getId() != null && Objects.equals(getId(), pessoaJuridica.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
