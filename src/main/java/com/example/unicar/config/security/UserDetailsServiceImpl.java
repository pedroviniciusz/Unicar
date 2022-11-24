package com.example.unicar.config.security;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findUsuarioByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrando com o username" + username));

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true ,true, usuario.getAuthorities());
    }
}
