/**
 * Clase de modelo para representar un Usuario.
 */
package appnotas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

  /**
   * Identificador único del usuario.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nombre de usuario.
   * Debe tener una longitud mínima de 4 caracteres y una longitud máxima de 255 caracteres.
   */
  @Size(min = 4, max = 255, message = "Longitud mínima del nombre de usuario: 4 caracteres")
  @Column(unique = true, nullable = false)
  private String username;

  /**
   * Dirección de correo electrónico del usuario.
   * Debe ser única y no nula.
   */
  @Column(unique = true, nullable = false)
  private String email;

  /**
   * Contraseña del usuario.
   * Debe tener una longitud mínima de 8 caracteres.
   */
  @Size(min = 8, message = "Longitud mínima de la contraseña: 8 caracteres")
  private String password;

  /**
   * Roles asignados al usuario.
   */
  @ElementCollection(fetch = FetchType.EAGER)
  List<UsuarioRol> usuarioRols;

}
