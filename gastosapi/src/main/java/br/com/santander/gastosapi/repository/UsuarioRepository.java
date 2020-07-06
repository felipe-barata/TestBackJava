package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  @Query(value = "select * from usuario where email = :email", nativeQuery = true)
  Optional<Usuario> findByEmail(@Param("email") String email);

  @Modifying
  @Transactional
  @Query(value = "update usuario set email = :email, nome = :nome where id = :id", nativeQuery = true)
  void atualizarUsuario(@Param("email") String email, @Param("nome") String nome, @Param("id") Integer id);

  @Modifying
  @Transactional
  @Query(value = "update usuario set senha = :senha where id = :id", nativeQuery = true)
  void atualizarSenhaUsuario(@Param("senha") String senha, @Param("id") Integer id);

}
