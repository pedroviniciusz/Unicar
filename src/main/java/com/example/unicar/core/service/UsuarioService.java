package com.example.unicar.core.service;

import com.example.unicar.config.security.EncodePassword;
import com.example.unicar.core.entity.Ticket;
import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.exception.EntityDuplicateException;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.exception.UsuarioException;
import com.example.unicar.core.repository.UsuarioRepository;
import com.example.unicar.config.Messages;
import com.example.unicar.core.util.CpfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.unicar.core.exception.ExceptionUtil.requireField;
import static com.example.unicar.config.Messages.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final TicketService ticketService;

    private final EncodePassword encoder;

    private final Messages messages;

    public Usuario findUsuarioById(UUID uuid) {
        Optional<Usuario> usuario = repository.findById(uuid);
        return usuario.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_USUARIO_COM_ESTE_UUID)));
    }

    public Usuario findUsuarioByUsername(String username) {
        Optional<Usuario> usuario = repository.findUsuarioByUsernameAndExcluidoFalse(username);
        return usuario.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_USUARIO_COM_ESTE_USERNAME)));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(Usuario usuario){
        
        if(existsUserByUsername(usuario.getUsername())){
            throw new EntityDuplicateException(messages.getMessage(JA_EXISTE_CADASTRO_COM_ESTE_USUARIO));
        }

        if(existsUserByCpf(usuario.getCpf())){
            throw new EntityDuplicateException(messages.getMessage(JA_EXISTE_CADASTRO_COM_ESTE_CPF));
        }

        if(!CpfUtil.isValido(usuario.getCpf())){
            throw new UsuarioException(messages.getMessage(CPF_INVALIDO));
        }

        usuario.setCpf(CpfUtil.formatarCpf(usuario.getCpf()));

        requireField(usuario.getPassword(), messages.getMessage(A_SENHA_NAO_PODE_SER_NULA));

        encoder.encodePassword(usuario);

        return repository.save(usuario);
        
    }

    public Usuario checkIn(UUID uuid){

        Usuario usuario = findUsuarioById(uuid);

        ticketService.checkIn(usuario);

        return usuario;
    }

    public Usuario checkOut(UUID uuid){

       Ticket ticket = ticketService.findTopByUsuarioUuidAndValidoIsTrueOrderByInclusaoDesc((uuid));

       ticketService.checkOut(ticket);

       return ticket.getUsuario();

    }

    public void deleteUsuarioById(UUID uuid) {

        if(!existsUserById(uuid)){
            throw new EntityDuplicateException(messages.getMessage(NAO_EXISTE_USUARIO_COM_ESTE_UUID));
        }

        repository.deleteById(uuid);

    }

    private boolean existsUserByUsername(String username){
        return repository.existsUsuarioByUsernameAndExcluidoFalse(username);
    }

    private boolean existsUserById(UUID uuid){
        return repository.existsById(uuid);
    }

    private boolean existsUserByCpf(String cpf){
        return repository.existsUsuarioByCpf(cpf);
    }

}
