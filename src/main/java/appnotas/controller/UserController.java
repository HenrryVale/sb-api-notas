/**
 * Controlador para la gestión de usuarios y notas.
 */
package appnotas.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import appnotas.dto.*;
import appnotas.model.Usuario;
import appnotas.model.Nota;
import appnotas.service.NotaService;
import appnotas.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(tags = "Usuario")
@RequiredArgsConstructor
public class UserController {

  private final UsuarioService usuarioService;
  private final NotaService notaService;
  private final ModelMapper modelMapper;

  /**
   * Realiza el inicio de sesión de un usuario.
   *
   * @param username El nombre de usuario.
   * @param password La contraseña.
   * @return Un objeto ResponseDTO que contiene información sobre el resultado del inicio de sesión.
   */
  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Algo salió mal"),
          @ApiResponse(code = 422, message = "Nombre de usuario/contraseña proporcionados no válidos")
  })
  public ResponseDTO<String> login(
          @ApiParam("Username") @RequestParam String username,
          @ApiParam("Password") @RequestParam String password) {
    return usuarioService.signin(username, password);
  }

  /**
   * Realiza el registro de un nuevo usuario.
   *
   * @param user Los datos del usuario a registrar.
   * @return Un objeto ResponseDTO que contiene información sobre el resultado del registro.
   */
  @PostMapping("/signup")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Algo salió mal"),
          @ApiResponse(code = 403, message = "Acceso denegado"),
          @ApiResponse(code = 422, message = "El nombre de usuario ya está en uso")
  })
  public ResponseDTO<String> signup(@ApiParam("Signup User") @RequestBody UsuarioDataDTO user) {
    return usuarioService.signup(modelMapper.map(user, Usuario.class));
  }

  /**
   * Registra una nueva nota para el usuario autenticado.
   *
   * @param req     La solicitud HTTP.
   * @param notaDTO Los datos de la nota a registrar.
   * @return Un objeto ResponseDTO que contiene información sobre el resultado del registro de la nota.
   */
  @PostMapping("/nota")
  @ApiOperation(value = "${UserController.nota}", authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Algo salió mal"),
          @ApiResponse(code = 403, message = "Acceso denegado"),
          @ApiResponse(code = 404, message = "El usuario no existe"),
          @ApiResponse(code = 500, message = "Token jwt caducado o no válido")
  })
  public ResponseDTO<Nota> nota(HttpServletRequest req, @ApiParam("Registrar nota") @RequestBody NotaDTO notaDTO) {
    UsuarioResponseDTO usuarioResponseDTO = modelMapper.map(usuarioService.whoami(req), UsuarioResponseDTO.class);
    Nota nota = modelMapper.map(notaDTO, Nota.class);
    nota.setUsuarioId(usuarioResponseDTO.getId());
    return notaService.crearNota(nota);
  }

  /**
   * Obtiene la lista de notas del usuario autenticado.
   *
   * @param req La solicitud HTTP.
   * @return Una lista de NotaResponseDTO que contiene las notas del usuario.
   */
  @GetMapping("/notas")
  @ApiOperation(value = "${UserController.notas}", authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Algo salió mal"),
          @ApiResponse(code = 403, message = "Acceso denegado"),
          @ApiResponse(code = 404, message = "El usuario no existe"),
          @ApiResponse(code = 500, message = "Token jwt caducado o no válido")
  })
  public List<NotaResponseDTO> notas(HttpServletRequest req) {
    UsuarioResponseDTO usuarioResponseDTO = modelMapper.map(usuarioService.whoami(req), UsuarioResponseDTO.class);
    List<Nota> notas = notaService.listarNotas(usuarioResponseDTO.getId());

    // Utilizar ModelMapper para mapear la lista de Nota a una lista de NotaResponseDTO
    List<NotaResponseDTO> notaResponseDTO = notas.stream()
            .map(nota -> modelMapper.map(nota, NotaResponseDTO.class))
            .collect(Collectors.toList());

    return notaResponseDTO;
  }
}
