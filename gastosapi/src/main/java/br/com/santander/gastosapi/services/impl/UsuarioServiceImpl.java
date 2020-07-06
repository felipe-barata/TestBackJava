package br.com.santander.gastosapi.services.impl;

import br.com.santander.gastosapi.domain.Usuario;
import br.com.santander.gastosapi.enums.PerfilEnum;
import br.com.santander.gastosapi.repository.UsuarioRepository;
import br.com.santander.gastosapi.services.UsuarioService;
import br.com.santander.gastosapi.utils.BCryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private BCryptUtils bCryptUtils;

  @Override
  public Optional<Usuario> persisteUsuario(Usuario usuario) {
    log.info("persisteUsuario - email: {}", usuario.getEmail());

    Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

    if (usuarioOptional.isEmpty()) {
      log.debug("persisteUsuario - usuario nao cadastrado");
      usuario.setSenha(bCryptUtils.gerarBCrypt(usuario.getSenha()));
      usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
      Usuario save = usuarioRepository.save(usuario);
      usuarioOptional = Optional.of(save);
    } else {
      usuarioRepository.atualizarUsuario(usuario.getEmail(), usuario.getNome(), usuario.getId());
      usuarioOptional = Optional.of(usuario);
    }
    return usuarioOptional;
  }

  @Override
  public Optional<Usuario> atualizaSenha(String email, String senha) {
    log.info("atualizaSenha - atualiza senha usuario, email: {}", email);
    Optional<Usuario> byEmail = usuarioRepository.findByEmail(email);
    if (byEmail.isPresent()) {
      Usuario usuario = byEmail.get();
      log.debug("atualizaSenha - encontrou usuario com id: {}", usuario.getId());
      String senhaCripto = bCryptUtils.gerarBCrypt(senha);
      usuarioRepository.atualizarSenhaUsuario(senhaCripto, usuario.getId());
      usuario.setSenha(senhaCripto);
      return Optional.of(usuario);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Usuario> buscarPorEmail(String email) {
    log.info("buscarPorEmail - email: {}", email);
    return usuarioRepository.findByEmail(email);
  }

  @Override
  public Optional<Usuario> buscaPorCodigo(Integer codigo) {
    return usuarioRepository.findById(codigo);
  }
}
