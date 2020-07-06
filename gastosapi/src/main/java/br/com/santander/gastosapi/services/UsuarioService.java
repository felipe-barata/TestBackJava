package br.com.santander.gastosapi.services;

import br.com.santander.gastosapi.domain.Usuario;

import java.util.Optional;

public interface UsuarioService {

  Optional<Usuario> persisteUsuario(Usuario usuario);

  Optional<Usuario> atualizaSenha(String email, String senha);

  Optional<Usuario> buscarPorEmail(String email);

  Optional<Usuario> buscaPorCodigo(Integer codigo);

}
