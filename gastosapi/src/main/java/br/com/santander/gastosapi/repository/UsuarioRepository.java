package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
