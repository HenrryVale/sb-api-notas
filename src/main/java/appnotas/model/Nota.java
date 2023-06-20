/**
 * Clase de modelo para representar una Nota.
 */
package appnotas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Nota {

    /**
     * Identificador único de la nota.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Título de la nota.
     * Debe tener una longitud mínima de 4 caracteres y una longitud máxima de 255 caracteres.
     */
    @Size(min = 4, max = 255, message = "Longitud mínima del titulo de la nota: 4 caracteres")
    private String titulo;

    /**
     * Contenido de la nota.
     * Debe tener una longitud mínima de 4 caracteres y una longitud máxima de 255 caracteres.
     */
    @Size(min = 4, max = 255, message = "Longitud mínima del contenido de la nota: 4 caracteres")
    private String contenido;

    /**
     * Identificador del usuario asociado a la nota.
     */
    private Integer usuarioId;

}
