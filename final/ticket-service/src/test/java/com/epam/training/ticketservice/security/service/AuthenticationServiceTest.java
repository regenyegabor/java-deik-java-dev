package com.epam.training.ticketservice.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.security.session.SessionManager;
import com.epam.training.ticketservice.security.session.TokenCollector;

import java.util.ArrayList;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class, PasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private SessionManager sessionManager;

    @MockBean
    private TokenCollector tokenCollector;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthenticationService#signIn(String, String)}
     */
    @Test
    void testSignIn2() throws Exception {
        when(userRepository.findUserByUserName((String) any())).thenReturn(new User("janedoe", "iloveyou", true));
        when(sessionManager.createSession((String) any(), anyBoolean())).thenReturn(UUID.randomUUID());
        doNothing().when(tokenCollector).addToken((UUID) any());
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        assertEquals(0, authenticationService.signIn("janedoe", "iloveyou"));
        verify(userRepository).findUserByUserName((String) any());
        verify(sessionManager).createSession((String) any(), anyBoolean());
        verify(tokenCollector).addToken((UUID) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#signIn(String, String)}
     */
    @Test
    void testSignIn4() throws Exception {
        when(userRepository.findUserByUserName((String) any())).thenReturn(new User("janedoe", "iloveyou", true));
        when(sessionManager.createSession((String) any(), anyBoolean())).thenReturn(null);
        doNothing().when(tokenCollector).addToken((UUID) any());
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        assertEquals(-3, authenticationService.signIn("janedoe", "iloveyou"));
        verify(userRepository).findUserByUserName((String) any());
        verify(sessionManager).createSession((String) any(), anyBoolean());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#signIn(String, String)}
     */
    @Test
    void testSignIn5() throws Exception {
        when(userRepository.findUserByUserName((String) any())).thenReturn(new User("janedoe", "iloveyou", true));
        when(sessionManager.createSession((String) any(), anyBoolean())).thenReturn(UUID.randomUUID());
        doNothing().when(tokenCollector).addToken((UUID) any());
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(false);
        assertEquals(-2, authenticationService.signIn("janedoe", "iloveyou"));
        verify(userRepository).findUserByUserName((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }


    /**
     * Method under test: {@link AuthenticationService#signOut()}
     */
    @Test
    void testSignOut3() {
        doNothing().when(sessionManager).killSession((UUID) any());

        ArrayList<UUID> uuidList = new ArrayList<>();
        uuidList.add(UUID.randomUUID());
        when(tokenCollector.getTokens()).thenReturn(uuidList);
        authenticationService.signOut();
        verify(sessionManager).killSession((UUID) any());
        verify(tokenCollector, atLeast(1)).getTokens();
    }

    /**
     * Method under test: {@link AuthenticationService#findUserByUserName(String)}
     */
    @Test
    void testFindUserByUserName2() throws Exception {
        User user = new User("janedoe", "iloveyou", true);

        when(userRepository.findUserByUserName((String) any())).thenReturn(user);
        assertSame(user, authenticationService.findUserByUserName("janedoe"));
        verify(userRepository).findUserByUserName((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#findUserByUserName(String)}
     */
    @Test
    void testFindUserByUserName3() throws Exception {
        when(userRepository.findUserByUserName((String) any())).thenThrow(new Exception());
        assertThrows(Exception.class, () -> authenticationService.findUserByUserName("janedoe"));
        verify(userRepository).findUserByUserName((String) any());
    }
}

