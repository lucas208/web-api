package rn.sead.gov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rn.sead.gov.model.generic.AbstractEntity;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends AbstractEntity {

    String nome;
    String email;
    String username;
    String password;
    String role;

    public Usuario(Long id, String nome, String email, String username, String password, String role) {
        super(id, null);
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
