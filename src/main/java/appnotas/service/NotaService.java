/**
 * Clase de servicio para la entidad Nota.
 */
package appnotas.service;

import lombok.RequiredArgsConstructor;
import appnotas.dto.ResponseDTO;
import appnotas.model.Nota;
import appnotas.repository.NotaRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;

    /**
     * Crea una nueva nota.
     *
     * @param nota La nota a crear.
     * @return Un objeto ResponseDTO que contiene información sobre el resultado de la operación de creación.
     */
    public ResponseDTO<Nota> crearNota(Nota nota) {
        String mensajesConcatenados = "Registrado correctamente!";
        Nota nuevaNota = null;
        try {
            nuevaNota = notaRepository.save(nota);
            return new ResponseDTO.Builder<Nota>().setMessage(mensajesConcatenados).setData(nuevaNota).build();
        } catch (ConstraintViolationException e) {
            StringJoiner mensajes = new StringJoiner(", ");
            e.getConstraintViolations().forEach(violation -> {
                mensajes.add(violation.getMessage());
            });
            mensajesConcatenados = mensajes.toString();
            return new ResponseDTO.Builder<Nota>().setMessage(mensajesConcatenados).build();
        } catch (Exception e) {
            mensajesConcatenados = " " + e.toString();
            return new ResponseDTO.Builder<Nota>().setMessage(mensajesConcatenados).build();
        }
    }

    /**
     * Lista todas las notas asociadas a un usuario.
     *
     * @param usuarioId El identificador del usuario.
     * @return Una lista de notas asociadas al usuario.
     */
    public List<Nota> listarNotas(Integer usuarioId) {
        return notaRepository.findAllByUsuarioId(usuarioId);
    }
}
