package appnotas.security;

import lombok.RequiredArgsConstructor;
import appnotas.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appnotas.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Usuario usuario = usuarioRepository.findByUsername(username);

    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario '" + username + "' no encontrado");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(usuario.getPassword())//
         .authorities(usuario.getUsuarioRols())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
