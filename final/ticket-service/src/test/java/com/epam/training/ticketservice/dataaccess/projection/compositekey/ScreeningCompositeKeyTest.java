package com.epam.training.ticketservice.dataaccess.projection.compositekey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ScreeningCompositeKeyTest {

    @Test
    void testCanEqual() {
        assertFalse((new ScreeningCompositeKey()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();

        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(3);
        roomProjection.setSeatRows(3);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection);
        screeningCompositeKey1.setRoomProjection(roomProjection);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertTrue(screeningCompositeKey.canEqual(screeningCompositeKey1));
    }

    @Test
    void testCanEqual3() {
        MovieProjection movieProjection = new MovieProjection();
        assertFalse((new ScreeningCompositeKey(movieProjection, new RoomProjection(), mock(java.sql.Date.class)))
                .canEqual("Other"));
    }

    @Test
    void testConstructor() {
        ScreeningCompositeKey actualScreeningCompositeKey = new ScreeningCompositeKey();
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        actualScreeningCompositeKey.setMovieProjection(movieProjection);
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        actualScreeningCompositeKey.setRoomProjection(roomProjection);
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualScreeningCompositeKey.setScreeningTime(fromResult);
        actualScreeningCompositeKey.toString();
        assertSame(movieProjection, actualScreeningCompositeKey.getMovieProjection());
        assertSame(roomProjection, actualScreeningCompositeKey.getRoomProjection());
        assertSame(fromResult, actualScreeningCompositeKey.getScreeningTime());
    }

    @Test
    void testConstructor2() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        ScreeningCompositeKey actualScreeningCompositeKey = new ScreeningCompositeKey(movieProjection, roomProjection,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        actualScreeningCompositeKey.setMovieProjection(movieProjection1);
        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        actualScreeningCompositeKey.setRoomProjection(roomProjection1);
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualScreeningCompositeKey.setScreeningTime(fromResult);
        actualScreeningCompositeKey.toString();
        MovieProjection movieProjection2 = actualScreeningCompositeKey.getMovieProjection();
        assertSame(movieProjection1, movieProjection2);
        assertEquals(movieProjection, movieProjection2);
        RoomProjection roomProjection2 = actualScreeningCompositeKey.getRoomProjection();
        assertSame(roomProjection1, roomProjection2);
        assertEquals(roomProjection, roomProjection2);
        assertSame(fromResult, actualScreeningCompositeKey.getScreeningTime());
    }

    @Test
    void testEquals() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, null);
    }


    @Test
    void testEquals2() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, "Different type to ScreeningCompositeKey");
    }


    @Test
    void testEquals3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertEquals(screeningCompositeKey, screeningCompositeKey);
        int expectedHashCodeResult = screeningCompositeKey.hashCode();
        assertEquals(expectedHashCodeResult, screeningCompositeKey.hashCode());
    }

    @Test
    void testEquals4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertEquals(screeningCompositeKey, screeningCompositeKey1);
        int expectedHashCodeResult = screeningCompositeKey.hashCode();
        assertEquals(expectedHashCodeResult, screeningCompositeKey1.hashCode());
    }

    @Test
    void testEquals5() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Dr");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, screeningCompositeKey1);
    }

    @Test
    void testEquals6() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(2L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, screeningCompositeKey1);
    }

    @Test
    void testEquals7() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, screeningCompositeKey1);
    }

    @Test
    void testEquals8() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey.setScreeningTime(null);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, screeningCompositeKey1);
    }

    @Test
    void testEquals9() {
        MovieProjection movieProjection = mock(MovieProjection.class);
        doNothing().when(movieProjection).setMovieGenre((String) any());
        doNothing().when(movieProjection).setMovieId((Long) any());
        doNothing().when(movieProjection).setMovieLength(anyInt());
        doNothing().when(movieProjection).setMovieTitle((String) any());
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertNotEquals(screeningCompositeKey, screeningCompositeKey1);
    }
}

