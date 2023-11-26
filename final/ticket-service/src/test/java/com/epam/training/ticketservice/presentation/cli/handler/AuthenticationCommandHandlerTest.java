package com.epam.training.ticketservice.presentation.cli.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.repository.impl.JpaUserRepository;
import com.epam.training.ticketservice.security.service.AuthenticationService;
import com.epam.training.ticketservice.security.session.SessionManager;
import com.epam.training.ticketservice.security.session.SessionManagerImplementation;
import com.epam.training.ticketservice.security.session.TokenCollector;
import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;

import java.util.Optional;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.shell.Availability;

class AuthenticationCommandHandlerTest {

    /**
     * Method under test: {@link AuthenticationCommandHandler#AuthenticationCommandHandler(AuthenticationService, SessionManager, TokenCollector)}
     */
    @Test
    void testConstructor2() {
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        AuthenticationCommandHandler actualAuthenticationCommandHandler = new AuthenticationCommandHandler(
                authenticationService, sessionManager1, new TokenCollectorImplementation());

        Availability isUserSignedInResult = actualAuthenticationCommandHandler.isUserSignedIn();
        assertFalse(isUserSignedInResult.isAvailable());
        Availability isUserPrivilegedResult = actualAuthenticationCommandHandler.isUserPrivileged();
        assertFalse(isUserPrivilegedResult.isAvailable());
        assertEquals("Please sign in to be able to use this command!", isUserPrivilegedResult.getReason());
        assertEquals("Please sign in to be able to use this command!", isUserSignedInResult.getReason());
        Availability isUserNotSignedInResult = actualAuthenticationCommandHandler.isUserNotSignedIn();
        assertNull(isUserNotSignedInResult.getReason());
        assertTrue(isUserNotSignedInResult.isAvailable());
    }


    /**
     * Method under test: {@link AuthenticationCommandHandler#signInPrivileged(String, String)}
     */
    @Test
    void testSignInPrivileged2() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        UserDao userDao = mock(UserDao.class);
        when(userDao.findByUserName((String) any())).thenReturn(Optional.of(userProjection));
        JpaUserRepository userRepository = new JpaUserRepository(userDao);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertEquals("Login failed due to incorrect credentials",
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .signInPrivileged("janedoe", "iloveyou"));
        verify(userDao).findByUserName((String) any());
    }

    /**
     * Method under test: {@link AuthenticationCommandHandler#signInPrivileged(String, String)}
     */
    @Test
    void testSignInPrivileged3() {
        UserDao userDao = mock(UserDao.class);
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        JpaUserRepository userRepository = new JpaUserRepository(userDao);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertEquals("Login failed due to incorrect credentials",
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .signInPrivileged("janedoe", "iloveyou"));
        verify(userDao).findByUserName((String) any());
    }

    /**
     * Method under test: {@link AuthenticationCommandHandler#signInPrivileged(String, String)}
     */
    @Test
    void testSignInPrivileged4() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        UserDao userDao = mock(UserDao.class);
        when(userDao.findByUserName((String) any())).thenReturn(Optional.of(userProjection));
        JpaUserRepository userRepository = new JpaUserRepository(userDao);
        LdapShaPasswordEncoder passwordEncoder = new LdapShaPasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertNull(
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .signInPrivileged("janedoe", "iloveyou"));
        verify(userDao).findByUserName((String) any());
    }


    /**
     * Method under test: {@link AuthenticationCommandHandler#signOut()}
     */
    @Test
    void testSignOut7() {
        TokenCollectorImplementation tokenCollectorImplementation = new TokenCollectorImplementation();
        tokenCollectorImplementation.addToken(UUID.randomUUID());
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                new SessionManagerImplementation(1, true, true), tokenCollectorImplementation);

        TokenCollectorImplementation tokenCollectorImplementation1 = new TokenCollectorImplementation();
        tokenCollectorImplementation1.addToken(UUID.randomUUID());
        assertEquals("You are now signed out!", (new AuthenticationCommandHandler(authenticationService,
                new SessionManagerImplementation(1, true, true), tokenCollectorImplementation1)).signOut());
    }


    /**
     * Method under test: {@link AuthenticationCommandHandler#describeAccount()}
     */
    @Test
    void testDescribeAccount2() {
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertEquals("You are not signed in",
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .describeAccount());
    }


}

