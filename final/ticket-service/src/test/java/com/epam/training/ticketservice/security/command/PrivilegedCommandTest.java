package com.epam.training.ticketservice.security.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

class PrivilegedCommandTest {

    /**
     * Method under test: {@link PrivilegedCommand#isUserPrivileged()}
     */
    @Test
    void testIsUserPrivileged2() {
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        SessionManagerImplementation sessionManager1 = new SessionManagerImplementation(1, true, true);

        assertEquals("Please sign in to be able to use this command!",
                (new AuthenticationCommandHandler(authenticationService, sessionManager1, new TokenCollectorImplementation()))
                        .isUserPrivileged()
                        .getReason());
    }


    /**
     * Method under test: {@link PrivilegedCommand#isUserPrivileged()}
     */
    @Test
    void testIsUserPrivileged4() {
        TokenCollectorImplementation tokenCollectorImplementation = new TokenCollectorImplementation();
        tokenCollectorImplementation.addToken(UUID.randomUUID());
        JpaUserRepository userRepository = new JpaUserRepository(mock(UserDao.class));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        AuthenticationService authenticationService = new AuthenticationService(userRepository, passwordEncoder,
                sessionManager, new TokenCollectorImplementation());

        assertEquals("Please sign in to be able to use this command!",
                (new AuthenticationCommandHandler(authenticationService, new SessionManagerImplementation(1, true, true),
                        tokenCollectorImplementation)).isUserPrivileged().getReason());
    }


}

