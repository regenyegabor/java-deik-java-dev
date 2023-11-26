package com.epam.training.ticketservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ScreeningTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Screening#Screening(Movie, Room, Date)}
     *   <li>{@link Screening#toString()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Screening actualScreening = new Screening(movie, room, fromResult);
        actualScreening.toString();
        Movie movie1 = actualScreening.getMovie();
        assertSame(movie, movie1);
        assertSame(fromResult, actualScreening.getScreeningTime());
        Room room1 = actualScreening.getRoom();
        assertSame(room, room1);
        assertEquals(3, movie1.getMovieLength());
        assertEquals(1, room1.getSeatRows());
        assertEquals(1, room1.getSeatColumns());
        assertEquals("Room Name", room1.getRoomName());
        assertEquals("Movie Genre", movie1.getMovieGenre());
        assertEquals("Dr", movie1.getMovieTitle());
    }
}

