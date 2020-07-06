package br.com.santander.gastosapi.security.services;

import br.com.santander.gastosapi.domain.Usuario;
import br.com.santander.gastosapi.security.JwtUserFactory;
import br.com.santander.gastosapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioService usuarioService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Usuario> usuario = usuarioService.buscarPorEmail(username);

    if (usuario.isPresent()) {
      return JwtUserFactory.create(usuario.get());
    }

    throw new UsernameNotFoundException("Email não encontrado.");
  }

}
