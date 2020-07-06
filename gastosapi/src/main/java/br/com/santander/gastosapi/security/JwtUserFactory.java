package br.com.santander.gastosapi.security;

import br.com.santander.gastosapi.domain.Usuario;
import br.com.santander.gastosapi.enums.PerfilEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

  private JwtUserFactory() {
  }

  /**
   * Converte e gera um JwtUser com base nos dados de um funcionário.
   *
   * @param usuario
   * @return JwtUser
   */
  public static JwtUser create(Usuario usuario) {
    return new JwtUser(Long.valueOf(usuario.getId()), usuario.getEmail(), usuario.getSenha(),
        mapToGrantedAuthorities(usuario.getPerfil()));
  }

  /**
   * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
   *
   * @param perfilEnum
   * @return List<GrantedAuthority>
   */
  private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
    return authorities;
  }

}
