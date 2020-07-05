package br.com.santander.gastosapi.services;

import br.com.santander.gastosapi.domain.Sistema;

import java.util.Optional;

public interface SistemaService {

  Optional<Sistema> encontraSistemaPorToken(String token);

  Optional<Sistema> incluiSistemaEAtribuiToken(Sistema sistema, String chaveToken);

}
