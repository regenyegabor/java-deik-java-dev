package com.epam.training.ticketservice.dataaccess.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MovieProjectionTest {

    @Test
    void testCanEqual() {
        assertFalse((new MovieProjection()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        MovieProjection movieProjection = new MovieProjection();

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertTrue(movieProjection.canEqual(movieProjection1));
    }

    @Test
    void testConstructor() {
        MovieProjection actualMovieProjection = new MovieProjection();
        actualMovieProjection.setMovieGenre("Movie Genre");
        actualMovieProjection.setMovieId(1L);
        actualMovieProjection.setMovieLength(3);
        actualMovieProjection.setMovieTitle("Dr");
        String actualToStringResult = actualMovieProjection.toString();
        assertEquals("Movie Genre", actualMovieProjection.getMovieGenre());
        assertEquals(1L, actualMovieProjection.getMovieId().longValue());
        assertEquals(3, actualMovieProjection.getMovieLength());
        assertEquals("Dr", actualMovieProjection.getMovieTitle());
        assertEquals("MovieProjection(movieId=1, movieTitle=Dr, movieGenre=Movie Genre, movieLength=3)",
                actualToStringResult);
    }


    @Test
    void testConstructor2() {
        MovieProjection actualMovieProjection = new MovieProjection(1L, "Dr", "Movie Genre", 3);
        actualMovieProjection.setMovieGenre("Movie Genre");
        actualMovieProjection.setMovieId(1L);
        actualMovieProjection.setMovieLength(3);
        actualMovieProjection.setMovieTitle("Dr");
        String actualToStringResult = actualMovieProjection.toString();
        assertEquals("Movie Genre", actualMovieProjection.getMovieGenre());
        assertEquals(1L, actualMovieProjection.getMovieId().longValue());
        assertEquals(3, actualMovieProjection.getMovieLength());
        assertEquals("Dr", actualMovieProjection.getMovieTitle());
        assertEquals("MovieProjection(movieId=1, movieTitle=Dr, movieGenre=Movie Genre, movieLength=3)",
                actualToStringResult);
    }

    @Test
    void testEquals() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        assertNotEquals(movieProjection, null);
    }


    @Test
    void testEquals2() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        assertNotEquals(movieProjection, "Different type to MovieProjection");
    }

    @Test
    void testEquals3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        assertEquals(movieProjection, movieProjection);
        int expectedHashCodeResult = movieProjection.hashCode();
        assertEquals(expectedHashCodeResult, movieProjection.hashCode());
    }

    @Test
    void testEquals4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertEquals(movieProjection, movieProjection1);
        int expectedHashCodeResult = movieProjection.hashCode();
        assertEquals(expectedHashCodeResult, movieProjection1.hashCode());
    }


    @Test
    void testEquals5() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Dr");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals6() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre(null);
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals7() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(2L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals8() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(null);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals9() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(1);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals10() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Mr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }


    @Test
    void testEquals11() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle(null);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertNotEquals(movieProjection, movieProjection1);
    }

    @Test
    void testEquals12() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre(null);
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre(null);
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertEquals(movieProjection, movieProjection1);
        int expectedHashCodeResult = movieProjection.hashCode();
        assertEquals(expectedHashCodeResult, movieProjection1.hashCode());
    }


    @Test
    void testEquals13() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(null);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(null);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        assertEquals(movieProjection, movieProjection1);
        int expectedHashCodeResult = movieProjection.hashCode();
        assertEquals(expectedHashCodeResult, movieProjection1.hashCode());
    }


    @Test
    void testEquals14() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle(null);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle(null);
        assertEquals(movieProjection, movieProjection1);
        int expectedHashCodeResult = movieProjection.hashCode();
        assertEquals(expectedHashCodeResult, movieProjection1.hashCode());
    }
}

