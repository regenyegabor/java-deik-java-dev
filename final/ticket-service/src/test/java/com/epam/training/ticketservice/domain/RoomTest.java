package com.epam.training.ticketservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoomTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Room#Room(String, int, int)}
     *   <li>{@link Room#toString()}
     * </ul>
     */
    @Test
    void testConstructor() {
        assertEquals("Room Room Name with 1 seats, 1 rows and 1 columns", (new Room("Room Name", 1, 1)).toString());
    }
}

