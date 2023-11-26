package com.epam.training.ticketservice.security.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TokenCollectorImplementation.class})
@ExtendWith(SpringExtension.class)
class TokenCollectorImplementationTest {
    @Autowired
    private TokenCollectorImplementation tokenCollectorImplementation;

    /**
     * Method under test: {@link TokenCollectorImplementation#addToken(UUID)}
     */
    @Test
    void testAddToken() {
        TokenCollectorImplementation tokenCollectorImplementation = new TokenCollectorImplementation();
        tokenCollectorImplementation.addToken(UUID.randomUUID());
        assertEquals(1, tokenCollectorImplementation.getTokens().size());
    }

    /**
     * Method under test: {@link TokenCollectorImplementation#removeToken(UUID)}
     */
    @Test
    void testRemoveToken() {
        tokenCollectorImplementation.removeToken(UUID.randomUUID());
        assertEquals(0, tokenCollectorImplementation.getTokens().size());
    }

    /**
     * Method under test: {@link TokenCollectorImplementation#removeToken(UUID)}
     */
    @Test
    void testRemoveToken2() {
        tokenCollectorImplementation.removeToken(null);
        assertEquals(0, tokenCollectorImplementation.getTokens().size());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link TokenCollectorImplementation}
     *   <li>{@link TokenCollectorImplementation#getTokens()}
     * </ul>
     */
    @Test
    void testConstructor() {
        TokenCollectorImplementation actualTokenCollectorImplementation = new TokenCollectorImplementation();
        ArrayList<UUID> expectedTokens = actualTokenCollectorImplementation.tokens;
        assertSame(expectedTokens, actualTokenCollectorImplementation.getTokens());
    }
}

