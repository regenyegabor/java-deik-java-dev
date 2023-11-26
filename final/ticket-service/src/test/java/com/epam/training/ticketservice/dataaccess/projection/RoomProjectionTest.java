package com.epam.training.ticketservice.dataaccess.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RoomProjectionTest {
    @Test
    void testCanEqual() {
        assertFalse((new RoomProjection()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        RoomProjection roomProjection = new RoomProjection();

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(3);
        roomProjection1.setSeatRows(3);
        assertTrue(roomProjection.canEqual(roomProjection1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#RoomProjection()}
     *   <li>{@link RoomProjection#setRoomId(Long)}
     *   <li>{@link RoomProjection#setRoomName(String)}
     *   <li>{@link RoomProjection#setSeatColumns(int)}
     *   <li>{@link RoomProjection#setSeatRows(int)}
     *   <li>{@link RoomProjection#toString()}
     *   <li>{@link RoomProjection#getRoomId()}
     *   <li>{@link RoomProjection#getRoomName()}
     *   <li>{@link RoomProjection#getSeatColumns()}
     *   <li>{@link RoomProjection#getSeatRows()}
     * </ul>
     */
    @Test
    void testConstructor() {
        RoomProjection actualRoomProjection = new RoomProjection();
        actualRoomProjection.setRoomId(1L);
        actualRoomProjection.setRoomName("Room Name");
        actualRoomProjection.setSeatColumns(1);
        actualRoomProjection.setSeatRows(1);
        String actualToStringResult = actualRoomProjection.toString();
        assertEquals(1L, actualRoomProjection.getRoomId().longValue());
        assertEquals("Room Name", actualRoomProjection.getRoomName());
        assertEquals(1, actualRoomProjection.getSeatColumns());
        assertEquals(1, actualRoomProjection.getSeatRows());
        assertEquals("RoomProjection(roomId=1, roomName=Room Name, seatRows=1, seatColumns=1)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#RoomProjection(Long, String, int, int)}
     *   <li>{@link RoomProjection#setRoomId(Long)}
     *   <li>{@link RoomProjection#setRoomName(String)}
     *   <li>{@link RoomProjection#setSeatColumns(int)}
     *   <li>{@link RoomProjection#setSeatRows(int)}
     *   <li>{@link RoomProjection#toString()}
     *   <li>{@link RoomProjection#getRoomId()}
     *   <li>{@link RoomProjection#getRoomName()}
     *   <li>{@link RoomProjection#getSeatColumns()}
     *   <li>{@link RoomProjection#getSeatRows()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        RoomProjection actualRoomProjection = new RoomProjection(1L, "Room Name", 1, 1);
        actualRoomProjection.setRoomId(1L);
        actualRoomProjection.setRoomName("Room Name");
        actualRoomProjection.setSeatColumns(1);
        actualRoomProjection.setSeatRows(1);
        String actualToStringResult = actualRoomProjection.toString();
        assertEquals(1L, actualRoomProjection.getRoomId().longValue());
        assertEquals("Room Name", actualRoomProjection.getRoomName());
        assertEquals(1, actualRoomProjection.getSeatColumns());
        assertEquals(1, actualRoomProjection.getSeatRows());
        assertEquals("RoomProjection(roomId=1, roomName=Room Name, seatRows=1, seatColumns=1)", actualToStringResult);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        assertNotEquals(roomProjection, null);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals2() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        assertNotEquals(roomProjection, "Different type to RoomProjection");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#equals(Object)}
     *   <li>{@link RoomProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        assertEquals(roomProjection, roomProjection);
        int expectedHashCodeResult = roomProjection.hashCode();
        assertEquals(expectedHashCodeResult, roomProjection.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#equals(Object)}
     *   <li>{@link RoomProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertEquals(roomProjection, roomProjection1);
        int expectedHashCodeResult = roomProjection.hashCode();
        assertEquals(expectedHashCodeResult, roomProjection1.hashCode());
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals5() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(2L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals6() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(null);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals7() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName(null);
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals8() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("com.epam.training.ticketservice.dataaccess.projection.RoomProjection");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals9() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(3);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Method under test: {@link RoomProjection#equals(Object)}
     */
    @Test
    void testEquals10() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(3);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertNotEquals(roomProjection, roomProjection1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#equals(Object)}
     *   <li>{@link RoomProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals11() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(null);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(null);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertEquals(roomProjection, roomProjection1);
        int expectedHashCodeResult = roomProjection.hashCode();
        assertEquals(expectedHashCodeResult, roomProjection1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RoomProjection#equals(Object)}
     *   <li>{@link RoomProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals12() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName(null);
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName(null);
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        assertEquals(roomProjection, roomProjection1);
        int expectedHashCodeResult = roomProjection.hashCode();
        assertEquals(expectedHashCodeResult, roomProjection1.hashCode());
    }
}

