package rn.sead.gov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rn.sead.gov.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByUsername(String username);
}
