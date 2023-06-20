/**
 * Clase de servicio para la gestión de usuarios.
 */
package appnotas.service;

import appnotas.dto.ResponseDTO;
import appnotas.model.Usuario;
import appnotas.repository.UsuarioRepository;
import appnotas.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * Realiza el inicio de sesión de un usuario.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return Un objeto ResponseDTO que contiene información sobre el resultado del inicio de sesión.
     */
    public ResponseDTO<String> signin(String username, String password) {
        String mensajesConcatenados = "Ingreso correcto!";
        try {
            if (username.isEmpty() || password.isEmpty()) {
                mensajesConcatenados = "Ingrese username y password.";
                return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).build();
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados)
                    .setData(jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getUsuarioRols()))
                    .build();
        } catch (AuthenticationException e) {
            mensajesConcatenados = "Nombre de usuario/contraseña proporcionados no válidos";
            return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).build();
        }
    }

    /**
     * Realiza el registro de un nuevo usuario.
     *
     * @param usuario El usuario a registrar.
     * @return Un objeto ResponseDTO que contiene información sobre el resultado del registro.
     */
    public ResponseDTO<String> signup(Usuario usuario) {
        String mensajesConcatenados = "Ingreso correcto!";
        String token = "";

        try {
            if (usuarioRepository.existsByUsername(usuario.getUsername())
                    || usuarioRepository.existsByEmail(usuario.getEmail())) {
                mensajesConcatenados = "El nombre de usuario/correo ya está en uso";
                return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).setData(token).build();
            }

            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            token = jwtTokenProvider.createToken(usuario.getUsername(), usuario.getUsuarioRols());

            return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).setData(token).build();
        } catch (ConstraintViolationException e) {
            StringJoiner mensajes = new StringJoiner(", ");
            e.getConstraintViolations().forEach(violation -> {
                mensajes.add(violation.getMessage());
            });
            mensajesConcatenados = mensajes.toString();
            return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).build();
        } catch (Exception e) {
            mensajesConcatenados = " " + e.toString();
            return new ResponseDTO.Builder<String>().setMessage(mensajesConcatenados).build();
        }
    }

    /**
     * Obtiene los datos del usuario actualmente autenticado.
     *
     * @param req La solicitud HTTP.
     * @return El objeto Usuario del usuario actual.
     */
    public Usuario whoami(HttpServletRequest req) {
        return usuarioRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }
}
