package rn.sead.gov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rn.sead.gov.model.Usuario;
import rn.sead.gov.repository.UsuarioRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findUsuarioByUsername(username);

        if(optional.isPresent()) {
            return new org.springframework.security.core.userdetails.User(optional.get().getUsername(), encoder().encode(optional.get().getPassword()), Collections.singletonList(new SimpleGrantedAuthority(optional.get().getRole())));
        }

        throw new UsernameNotFoundException("User not found");
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
