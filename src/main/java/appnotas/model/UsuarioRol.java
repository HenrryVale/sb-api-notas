/**
 * Enumeración que representa los roles de usuario en la aplicación.
 */
package appnotas.model;

import org.springframework.security.core.GrantedAuthority;

public enum UsuarioRol implements GrantedAuthority {

  /**
   * Rol de usuario regular.
   */
  ROLE_USUARIO,

  /**
   * Rol de administrador.
   */
  ROLE_ADMIN;

  /**
   * Obtiene la autoridad del rol.
   *
   * @return La autoridad del rol.
   */
  @Override
  public String getAuthority() {
    return name();
  }

}
