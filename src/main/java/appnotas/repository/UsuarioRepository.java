/**
 * Interfaz de repositorio para la entidad Usuario.
 */
package appnotas.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import appnotas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  /**
   * Verifica si existe un usuario con el nombre de usuario especificado.
   *
   * @param username El nombre de usuario a verificar.
   * @return true si existe un usuario con el nombre de usuario especificado, false de lo contrario.
   */
  boolean existsByUsername(String username);

  /**
   * Busca y devuelve un usuario por su nombre de usuario.
   *
   * @param username El nombre de usuario a buscar.
   * @return El usuario encontrado o null si no se encuentra ningún usuario con el nombre de usuario especificado.
   */
  Usuario findByUsername(String username);

  /**
   * Verifica si existe un usuario con el correo electrónico especificado.
   *
   * @param email El correo electrónico a verificar.
   * @return true si existe un usuario con el correo electrónico especificado, false de lo contrario.
   */
  boolean existsByEmail(String email);

}
