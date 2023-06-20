/**
 * Clase de prueba unitaria para la clase NotaService.
 */
package appnotastest.service;

import appnotas.dto.ResponseDTO;
import appnotas.model.Nota;
import appnotas.repository.NotaRepository;
import appnotas.service.NotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NotaServiceTest {

    @Mock
    private NotaRepository notaRepository;

    private NotaService notaService;

    /**
     * Configuración inicial para cada prueba.
     * Inicializa los objetos simulados y crea una instancia de NotaService.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        notaService = new NotaService(notaRepository);
    }

    /**
     * Prueba unitaria para el método crearNota() cuando se proporciona una nota válida.
     * Verifica que se devuelva un objeto ResponseDTO con el mensaje esperado y la nota guardada.
     */
    @Test
    public void testCrearNota() {
        Nota nota = new Nota();

        when(notaRepository.save(nota)).thenReturn(nota);

        ResponseDTO<Nota> response = notaService.crearNota(nota);

        assertNotNull(response);
        assertEquals("Registrado correctamente!", response.getMessage());
        assertEquals(nota, response.getData());
    }

    /**
     * Prueba unitaria para el método listarNotas() cuando se proporciona un usuarioId.
     * Verifica que se devuelva una lista de notas correspondientes al usuarioId dado.
     */
    @Test
    public void testListarNotas() {
        int usuarioId = 1;
        List<Nota> notas = new ArrayList<>();

        when(notaRepository.findAllByUsuarioId(usuarioId)).thenReturn(notas);

        List<Nota> result = notaService.listarNotas(usuarioId);

        assertEquals(notas, result);
    }

}
