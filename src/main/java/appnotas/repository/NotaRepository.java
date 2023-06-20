/**
 * Interfaz de repositorio para la entidad Nota.
 */
package appnotas.repository;

import appnotas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    /**
     * Obtiene todas las notas asociadas a un usuario específico.
     *
     * @param usuarioId El identificador del usuario.
     * @return Una lista de notas asociadas al usuario.
     */
    public List<Nota> findAllByUsuarioId(Integer usuarioId);
}
