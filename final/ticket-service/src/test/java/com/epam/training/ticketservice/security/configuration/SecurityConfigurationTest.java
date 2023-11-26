package com.epam.training.ticketservice.security.configuration;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfiguration.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigurationTest {
    @Autowired
    private SecurityConfiguration securityConfiguration;

    /**
     * Method under test: {@link SecurityConfiguration#tokenCollector()}
     */
    @Test
    void testTokenCollector() {
        assertTrue(securityConfiguration.tokenCollector() instanceof TokenCollectorImplementation);
    }

    /**
     * Method under test: {@link SecurityConfiguration#typeBCryptPasswordEncoder()}
     */
    @Test
    void testTypeBCryptPasswordEncoder() {


        SecurityConfiguration securityConfiguration = new SecurityConfiguration();
        securityConfiguration.typeBCryptPasswordEncoder();
        assertTrue(securityConfiguration.tokenCollector().getTokens().isEmpty());
    }

    /**
     * Method under test: {@link SecurityConfiguration#sessionManager()}
     */
    @Test
    void testSessionManager() {


        assertNull((new SecurityConfiguration()).sessionManager().findSessionByToken(null));
    }
}

