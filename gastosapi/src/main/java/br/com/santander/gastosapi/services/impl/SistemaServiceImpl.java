package br.com.santander.gastosapi.services.impl;

import br.com.santander.gastosapi.domain.Sistema;
import br.com.santander.gastosapi.repository.SistemaRepository;
import br.com.santander.gastosapi.services.SistemaService;
import br.com.santander.gastosapi.utils.BCryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SistemaServiceImpl implements SistemaService {

  @Autowired
  private SistemaRepository sistemaRepository;

  @Autowired
  private BCryptUtils bCryptUtils;

  @Override
  public Optional<Sistema> encontraSistemaPorToken(String token) {
    log.info("encontraSistemaPorToken");
    return sistemaRepository.findByToken(token);
  }

  @Override
  public Optional<Sistema> incluiSistemaEAtribuiToken(Sistema sistema, String chaveToken) {
    log.info("incluiSistemaEAtribuiToken - codigo: {}", sistema.getCodigoSistema());
    if (sistemaRepository.findByCodigoSistema(sistema.getCodigoSistema()).isPresent()) {
      log.warn("incluiSistemaEAtribuiToken - codigo: {} já atribuido a um sistema", sistema.getCodigoSistema());
      return Optional.empty();
    }

    //gerar token
    sistema.setToken(bCryptUtils.gerarBCrypt(chaveToken));

    Sistema save = sistemaRepository.save(sistema);
    if (save != null) {
      log.info("incluiSistemaEAtribuiToken - sistema: {} salvo com id: {}", save.getCodigoSistema(), save.getId());
      return Optional.of(save);
    }

    return Optional.empty();
  }

}
