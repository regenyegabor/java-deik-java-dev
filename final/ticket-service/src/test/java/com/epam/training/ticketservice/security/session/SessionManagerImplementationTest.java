package com.epam.training.ticketservice.security.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SessionManagerImplementation.class})
@ExtendWith(SpringExtension.class)
class SessionManagerImplementationTest {
    @Autowired
    private SessionManagerImplementation sessionManagerImplementation;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SessionManagerImplementation#SessionManagerImplementation(int, boolean, boolean)}
     *   <li>{@link SessionManagerImplementation#setMultipleSessionsAllowed(boolean)}
     *   <li>{@link SessionManagerImplementation#setSessionTimeInMinutes(int)}
     *   <li>{@link SessionManagerImplementation#setAutoRenewSession(boolean)}
     * </ul>
     */
    @Test
    void testConstructor() {
        SessionManagerImplementation actualSessionManagerImplementation = new SessionManagerImplementation(1, true, true);
        actualSessionManagerImplementation.setMultipleSessionsAllowed(true);
        actualSessionManagerImplementation.setSessionTimeInMinutes(1);
        actualSessionManagerImplementation.setAutoRenewSession(true);
    }



    /**
     * Method under test: {@link SessionManagerImplementation#killSession(UUID)}
     */
    @Test
    void testKillSession() {

        SessionManagerImplementation sessionManagerImplementation = new SessionManagerImplementation(1, true, true);
        sessionManagerImplementation.killSession(UUID.randomUUID());
        assertNull(sessionManagerImplementation.findSessionByToken(null));
    }

    /**
     * Method under test: {@link SessionManagerImplementation#renewSession(UUID)}
     */
    @Test
    void testRenewSession() {

        SessionManagerImplementation sessionManagerImplementation = new SessionManagerImplementation(1, true, true);
        sessionManagerImplementation.renewSession(UUID.randomUUID());
        assertNull(sessionManagerImplementation.findSessionByToken(null));
    }

    /**
     * Method under test: {@link SessionManagerImplementation#renewSession(UUID)}
     */
    @Test
    void testRenewSession2() {

        SessionManagerImplementation sessionManagerImplementation = new SessionManagerImplementation(1, false, true);
        sessionManagerImplementation.renewSession(UUID.randomUUID());
        assertNull(sessionManagerImplementation.findSessionByToken(null));
    }

    /**
     * Method under test: {@link SessionManagerImplementation#isSessionAlive(UUID)}
     */
    @Test
    void testIsSessionAlive() {
        assertFalse(sessionManagerImplementation.isSessionAlive(UUID.randomUUID()));
    }

    /**
     * Method under test: {@link SessionManagerImplementation#isPrivilegedSession(UUID)}
     */
    @Test
    void testIsPrivilegedSession() {
        assertFalse(sessionManagerImplementation.isPrivilegedSession(UUID.randomUUID()));
    }


    /**
     * Method under test: {@link SessionManagerImplementation#findSessionByToken(UUID)}
     */
    @Test
    void testFindSessionByToken() {
        assertNull(sessionManagerImplementation.findSessionByToken(UUID.randomUUID()));
    }


}

