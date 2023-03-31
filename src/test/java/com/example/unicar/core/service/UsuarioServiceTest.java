package com.example.unicar.core.service;

import com.example.unicar.config.security.EncodePassword;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.exception.UsuarioException;
import com.example.unicar.core.message.Messages;
import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.exception.EntityDuplicateException;
import com.example.unicar.core.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static com.example.unicar.core.message.Messages.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {Messages.class, MessageSourceAutoConfiguration.class})
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService service;

    @Mock
    UsuarioRepository repository;

    @Mock
    TicketService ticketService;

    @Mock
    EncodePassword encodePassword;

    @Mock
    Usuario usuario;

    @Mock
    Messages messages;

    @BeforeEach
    void setUp() {

        service = new UsuarioService(repository, ticketService, encodePassword, messages);

        usuario = Usuario.builder()
                .uuid(UUID.fromString("80bf3d42-cb3f-11ed-afa1-0242ac120002"))
                .username("Pedrokszzz")
                .password("123")
                .nome("Pedro")
                .cpf("038.433.140-80")
                .build();
    }

    @Test
    @DisplayName("Deve lançar execeção ao tentar cadastrar um usuário com um username já utilizado")
    void deveLancarExcecaoAoCadastrarUsernameExistente() {

        when(repository.existsUsuarioByUsername(usuario.getUsername())).thenReturn(Boolean.TRUE);

        final EntityDuplicateException exception = assertThrows(EntityDuplicateException.class, () -> service.create(usuario));
        assertThat(exception).hasMessage(messages.getMessage(JA_EXISTE_CADASTRO_COM_ESTE_USUARIO));

        verify(repository, times(0)).save(usuario);
    }

    @Test
    @DisplayName("Deve lançar execeção ao tentar cadastrar um usuário com um CPF já utilizado")
    void deveLancarExcecaoAoCadastrarCpfExistente() {

        when(repository.existsUsuarioByCpf(usuario.getCpf())).thenReturn(Boolean.TRUE);

        final EntityDuplicateException exception = assertThrows(EntityDuplicateException.class, () -> service.create(usuario));
        assertThat(exception).hasMessage(messages.getMessage(JA_EXISTE_CADASTRO_COM_ESTE_CPF));

        verify(repository, times(0)).save(usuario);
    }

    @Test
    @DisplayName("Deve lançar execeção ao informar CPF inválido")
    void deveLancarExcecaoAoInformarCpfInvalido() {

        usuario.setCpf("777-333-444-113-50");

        final UsuarioException exception = assertThrows(UsuarioException.class, () -> service.create(usuario));
        assertThat(exception).hasMessage(messages.getMessage(CPF_INVALIDO));

        verify(repository, times(0)).save(usuario);
    }

    @Test
    @DisplayName("Deve lançar execeção ao informar senha vazia")
    void deveLancarExcecaoAoInformarSenhaVazia() {

        usuario.setPassword("");

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.create(usuario));
        assertThat(exception).hasMessage(messages.getMessage(A_SENHA_NAO_PODE_SER_NULA));

        verify(repository, times(0)).save(usuario);
    }

    @Test
    @DisplayName("Deve inserir usuário no banco")
    void deveInserirUsuarioNoBanco() {

        when(repository.existsUsuarioByUsername(usuario.getUsername())).thenReturn(Boolean.FALSE);
        when(repository.existsUsuarioByCpf(usuario.getCpf())).thenReturn(Boolean.FALSE);

        service.create(usuario);

        verify(repository, times(1)).save(usuario);

    }

    @Test
    @DisplayName("Deve lançar execeção ao informar usuário inexsitente")
    void deveLancarExecaoAoInformarUsuarioInesxistente() {

        when(repository.existsById(usuario.getUuid())).thenReturn(Boolean.FALSE);

        final EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.deleteUsuarioById(usuario.getUuid()));
        assertThat(exception).hasMessage(messages.getMessage(NAO_EXISTE_USUARIO_COM_ESTE_UUID));

        verify(repository, times(0)).deleteById(usuario.getUuid());
    }

    @Test
    @DisplayName("Deve deletar usuário do banco")
    void deveDeletarUsuarioDoBanco() {

        when(repository.existsById(usuario.getUuid())).thenReturn(Boolean.TRUE);

        service.deleteUsuarioById(usuario.getUuid());

        verify(repository, times(1)).deleteById(usuario.getUuid());

    }

}
