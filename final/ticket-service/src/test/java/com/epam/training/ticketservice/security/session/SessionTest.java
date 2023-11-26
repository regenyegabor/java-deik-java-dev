package com.epam.training.ticketservice.security.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class SessionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Session#Session(String, int, boolean)}
     *   <li>{@link Session#setTimeOfDeath(Date)}
     *   <li>{@link Session#getTimeOfDeath()}
     *   <li>{@link Session#getToken()}
     *   <li>{@link Session#getUserName()}
     *   <li>{@link Session#isPrivilegedSession()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Session actualSession = new Session("janedoe", 1, true);
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualSession.setTimeOfDeath(fromResult);
        assertSame(fromResult, actualSession.getTimeOfDeath());
        UUID expectedToken = actualSession.token;
        assertSame(expectedToken, actualSession.getToken());
        assertEquals("janedoe", actualSession.getUserName());
        assertTrue(actualSession.isPrivilegedSession());
    }

    /**
     * Method under test: {@link Session#Session(String, int, boolean)}
     */
    @Test
    void testConstructor2() {
        Session actualSession = new Session("janedoe", 1, true);

        assertTrue(actualSession.isPrivilegedSession());
        assertEquals("janedoe", actualSession.getUserName());
        UUID expectedToken = actualSession.token;
        assertSame(expectedToken, actualSession.getToken());
    }
}

