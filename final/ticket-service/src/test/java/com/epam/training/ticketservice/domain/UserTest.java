package com.epam.training.ticketservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Method under test: {@link User#toString()}
     */
    @Test
    void testToString() {
        assertEquals("janedoe, privileged", (new User("janedoe", "iloveyou", true)).toString());
        assertEquals("janedoe, not privileged", (new User("janedoe", "iloveyou", false)).toString());
    }
}

