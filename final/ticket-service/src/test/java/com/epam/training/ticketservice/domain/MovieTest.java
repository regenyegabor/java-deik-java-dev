package com.epam.training.ticketservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MovieTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movie#Movie(String, String, int)}
     *   <li>{@link Movie#toString()}
     * </ul>
     */
    @Test
    void testConstructor() {
        assertEquals("Dr (movie genre, 3 minutes)", (new Movie("Dr", "Movie Genre", 3)).toString());
    }
}

