/**
 * Clase principal de la aplicación AppNotaServiceApp.
 */
package appnotas;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import appnotas.service.UsuarioService;

@SpringBootApplication
@RequiredArgsConstructor
public class AppNotaServiceApp {

  final UsuarioService usuarioService;

  /**
   * Método principal que inicia la aplicación.
   *
   * @param args Los argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    SpringApplication.run(AppNotaServiceApp.class, args);
  }

  /**
   * Configura un objeto ModelMapper para realizar mapeos entre objetos.
   *
   * @return Un objeto ModelMapper configurado.
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
