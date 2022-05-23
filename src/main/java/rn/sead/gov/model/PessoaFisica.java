package rn.sead.gov.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    private String nacionalidade;

    @Column(name = "estado_civil")
    private String estadoCivil;

    private String sexo;

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
            joinColumns = {@JoinColumn(name = "pessoa_id")},
            inverseJoinColumns = {@JoinColumn(name = "deficiencia_id")})
    private List<Deficiencia> deficiencias;

    public PessoaFisica(String nome, String cpf, String nacionalidade, String sexo, Date dataNascimento, Endereco endereco) {
        super(nome, null);
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.rg = null;
        this.uf = null;
        this.estadoCivil = null;
        this.pai = null;
        this.mae = null;
        this.nomeSocial = null;
        this.servidor = null;
    }

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
