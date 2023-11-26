package com.epam.training.ticketservice.security.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.presentation.cli.handler.AuthenticationCommandHandler;
import com.epam.training.ticketservice.repository.impl.JpaUserRepository;
import com.epam.training.ticketservice.security.service.AuthenticationService;
import com.epam.training.ticketservice.security.session.SessionManagerImplementation;
import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.shell.Availability;

class SecuredCommandTest {

    /**
     * Method under test: {@link SecuredCommand#isUserSignedIn()}
     */
    @Test
    void testIsUserSignedIn2() {
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertEquals("Please sign in to be able to use this command!",
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .isUserSignedIn()
                        .getReason());
    }


    /**
     * Method under test: {@link SecuredCommand#isUserSignedIn()}
     */
    @Test
    void testIsUserSignedIn4() {
        TokenCollectorImplementation tokenCollectorImplementation = new TokenCollectorImplementation();
        tokenCollectorImplementation.addToken(UUID.randomUUID());
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        assertEquals("Please sign in to be able to use this command!",
                (new AuthenticationCommandHandler(authenticationService, new SessionManagerImplementation(1, true, true),
                        tokenCollectorImplementation)).isUserSignedIn().getReason());
    }


    /**
     * Method under test: {@link SecuredCommand#isUserNotSignedIn()}
     */
    @Test
    void testIsUserNotSignedIn2() {
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertNull(
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .isUserNotSignedIn()
                        .getReason());
    }


    /**
     * Method under test: {@link SecuredCommand#isUserNotSignedIn()}
     */
    @Test
    void testIsUserNotSignedIn4() {
        TokenCollectorImplementation tokenCollectorImplementation = new TokenCollectorImplementation();
        tokenCollectorImplementation.addToken(UUID.randomUUID());
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        assertEquals("You can't use this command if you are signed in!",
                (new AuthenticationCommandHandler(authenticationService, new SessionManagerImplementation(1, true, true),
                        tokenCollectorImplementation)).isUserNotSignedIn().getReason());
    }
}

