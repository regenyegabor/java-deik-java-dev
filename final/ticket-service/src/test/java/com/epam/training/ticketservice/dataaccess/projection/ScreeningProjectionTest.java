package com.epam.training.ticketservice.dataaccess.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.epam.training.ticketservice.dataaccess.projection.compositekey.ScreeningCompositeKey;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ScreeningProjectionTest {
    /**
     * Method under test: {@link ScreeningProjection#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new ScreeningProjection()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ScreeningProjection#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        ScreeningProjection screeningProjection = new ScreeningProjection();

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

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey);
        assertTrue(screeningProjection.canEqual(screeningProjection1));
    }

    /**
     * Method under test: {@link ScreeningProjection#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
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

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey.setScreeningTime(mock(java.sql.Date.class));

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);
        assertFalse(screeningProjection.canEqual("Other"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScreeningProjection#ScreeningProjection()}
     *   <li>{@link ScreeningProjection#setScreeningCompositeKey(ScreeningCompositeKey)}
     *   <li>{@link ScreeningProjection#toString()}
     *   <li>{@link ScreeningProjection#getScreeningCompositeKey()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ScreeningProjection actualScreeningProjection = new ScreeningProjection();
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
        actualScreeningProjection.setScreeningCompositeKey(screeningCompositeKey);
        actualScreeningProjection.toString();
        assertSame(screeningCompositeKey, actualScreeningProjection.getScreeningCompositeKey());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScreeningProjection#ScreeningProjection(ScreeningCompositeKey)}
     *   <li>{@link ScreeningProjection#setScreeningCompositeKey(ScreeningCompositeKey)}
     *   <li>{@link ScreeningProjection#toString()}
     *   <li>{@link ScreeningProjection#getScreeningCompositeKey()}
     * </ul>
     */
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

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ScreeningProjection actualScreeningProjection = new ScreeningProjection(screeningCompositeKey);
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
        actualScreeningProjection.setScreeningCompositeKey(screeningCompositeKey1);
        actualScreeningProjection.toString();
        ScreeningCompositeKey screeningCompositeKey2 = actualScreeningProjection.getScreeningCompositeKey();
        assertSame(screeningCompositeKey1, screeningCompositeKey2);
        assertEquals(screeningCompositeKey, screeningCompositeKey2);
    }

    /**
     * Method under test: {@link ScreeningProjection#equals(Object)}
     */
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

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);
        assertNotEquals(screeningProjection, null);
    }

    /**
     * Method under test: {@link ScreeningProjection#equals(Object)}
     */
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

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);
        assertNotEquals(screeningProjection, "Different type to ScreeningProjection");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScreeningProjection#equals(Object)}
     *   <li>{@link ScreeningProjection#hashCode()}
     * </ul>
     */
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

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);
        assertEquals(screeningProjection, screeningProjection);
        int expectedHashCodeResult = screeningProjection.hashCode();
        assertEquals(expectedHashCodeResult, screeningProjection.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScreeningProjection#equals(Object)}
     *   <li>{@link ScreeningProjection#hashCode()}
     * </ul>
     */
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

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

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

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey1);
        assertEquals(screeningProjection, screeningProjection1);
        int expectedHashCodeResult = screeningProjection.hashCode();
        assertEquals(expectedHashCodeResult, screeningProjection1.hashCode());
    }

    /**
     * Method under test: {@link ScreeningProjection#equals(Object)}
     */
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

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

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

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey1);
        assertNotEquals(screeningProjection, screeningProjection1);
    }

    /**
     * Method under test: {@link ScreeningProjection#equals(Object)}
     */
    @Test
    void testEquals6() {
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
        ScreeningCompositeKey screeningCompositeKey = mock(ScreeningCompositeKey.class);
        doNothing().when(screeningCompositeKey).setMovieProjection((MovieProjection) any());
        doNothing().when(screeningCompositeKey).setRoomProjection((RoomProjection) any());
        doNothing().when(screeningCompositeKey).setScreeningTime((Date) any());
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

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

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey1);
        assertNotEquals(screeningProjection, screeningProjection1);
    }
}

