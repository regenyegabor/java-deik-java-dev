package com.epam.training.ticketservice.presentation.cli.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.repository.impl.JpaRoomRepository;
import com.epam.training.ticketservice.security.session.SessionManager;
import com.epam.training.ticketservice.security.session.SessionManagerImplementation;
import com.epam.training.ticketservice.security.session.TokenCollector;
import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;
import com.epam.training.ticketservice.service.RoomService;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Availability;

class RoomCommandHandlerTest {

    /**
     * Method under test: {@link RoomCommandHandler#RoomCommandHandler(SessionManager, TokenCollector, RoomService)}
     */
    @Test
    void testConstructor2() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        RoomCommandHandler actualRoomCommandHandler = new RoomCommandHandler(sessionManager, tokenCollector,
                new RoomService(new JpaRoomRepository(mock(RoomDao.class))));

        Availability isUserSignedInResult = actualRoomCommandHandler.isUserSignedIn();
        assertFalse(isUserSignedInResult.isAvailable());
        Availability isUserPrivilegedResult = actualRoomCommandHandler.isUserPrivileged();
        assertFalse(isUserPrivilegedResult.isAvailable());
        assertEquals("Please sign in to be able to use this command!", isUserPrivilegedResult.getReason());
        assertEquals("Please sign in to be able to use this command!", isUserSignedInResult.getReason());
        Availability isUserNotSignedInResult = actualRoomCommandHandler.isUserNotSignedIn();
        assertNull(isUserNotSignedInResult.getReason());
        assertTrue(isUserNotSignedInResult.isAvailable());
    }


    /**
     * Method under test: {@link RoomCommandHandler#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom2() {
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
        Optional<RoomProjection> ofResult = Optional.of(roomProjection1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Room with title 'Room Name' is already exists!",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                        .createRoom("Room Name", 1, 1));
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom3() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                .createRoom("Room Name", 1, 1));
        verify(roomDao).save((RoomProjection) any());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom4() {
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
        Optional<RoomProjection> ofResult = Optional.of(roomProjection1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        new JpaRoomRepository(roomDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.service.RoomService.createRoom(String, int, int)\" because"
                        + " \"this.roomService\" is null",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).createRoom("Room Name", 1,
                        1));
    }

    /**
     * Method under test: {@link RoomCommandHandler#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom5() throws Exception {
        JpaRoomRepository jpaRoomRepository = mock(JpaRoomRepository.class);
        doNothing().when(jpaRoomRepository).createRoom((Room) any());
        RoomService roomService = new RoomService(jpaRoomRepository);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                .createRoom("foo", 1, 1));
        verify(jpaRoomRepository).createRoom((Room) any());
    }


    /**
     * Method under test: {@link RoomCommandHandler#listRooms()}
     */
    @Test
    void testListRooms2() {
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.findAll()).thenReturn(new ArrayList<>());
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("There are no rooms at the moment",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService)).listRooms());
        verify(roomDao).findAll();
    }

    /**
     * Method under test: {@link RoomCommandHandler#listRooms()}
     */
    @Test
    void testListRooms3() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("There are no rooms at the moment");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ArrayList<RoomProjection> roomProjectionList = new ArrayList<>();
        roomProjectionList.add(roomProjection);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.findAll()).thenReturn(roomProjectionList);
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Room There are no rooms at the moment with 1 seats, 1 rows and 1 columns\n",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService)).listRooms());
        verify(roomDao).findAll();
    }

    /**
     * Method under test: {@link RoomCommandHandler#listRooms()}
     */
    @Test
    void testListRooms4() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("There are no rooms at the moment");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(2L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(0);
        roomProjection1.setSeatRows(0);

        ArrayList<RoomProjection> roomProjectionList = new ArrayList<>();
        roomProjectionList.add(roomProjection1);
        roomProjectionList.add(roomProjection);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.findAll()).thenReturn(roomProjectionList);
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Room Room Name with 0 seats, 0 rows and 0 columns\n"
                        + "Room There are no rooms at the moment with 1 seats, 1 rows and 1 columns\n",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService)).listRooms());
        verify(roomDao).findAll();
    }


    /**
     * Method under test: {@link RoomCommandHandler#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom2() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection1);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                .updateRoom("Room Name", 1, 1));
        verify(roomDao).save((RoomProjection) any());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom3() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Room 'Room Name' not found!",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                        .updateRoom("Room Name", 1, 1));
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom4() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        RoomDao roomDao = mock(RoomDao.class);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection1);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        new JpaRoomRepository(roomDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Room 'Room Name' not found!",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).updateRoom("Room Name", 1,
                        1));
    }

    /**
     * Method under test: {@link RoomCommandHandler#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom5() throws Exception {
        JpaRoomRepository jpaRoomRepository = mock(JpaRoomRepository.class);
        doNothing().when(jpaRoomRepository).updateRoomByRoom((Room) any());
        RoomService roomService = new RoomService(jpaRoomRepository);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(0, true, true);

        assertNull((new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                .updateRoom("Room Name", 1, 1));
        verify(jpaRoomRepository).updateRoomByRoom((Room) any());
    }


    /**
     * Method under test: {@link RoomCommandHandler#deleteRoom(String)}
     */
    @Test
    void testDeleteRoom2() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);
        RoomDao roomDao = mock(RoomDao.class);
        doNothing().when(roomDao).deleteByRoomName((String) any());
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                .deleteRoom("Room Name"));
        verify(roomDao).findByRoomName((String) any());
        verify(roomDao).deleteByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#deleteRoom(String)}
     */
    @Test
    void testDeleteRoom3() {
        RoomDao roomDao = mock(RoomDao.class);
        doNothing().when(roomDao).deleteByRoomName((String) any());
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        RoomService roomService = new RoomService(new JpaRoomRepository(roomDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Room not found with 'Room Name' name!",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), roomService))
                        .deleteRoom("Room Name"));
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomCommandHandler#deleteRoom(String)}
     */
    @Test
    void testDeleteRoom4() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);
        RoomDao roomDao = mock(RoomDao.class);
        doNothing().when(roomDao).deleteByRoomName((String) any());
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        new JpaRoomRepository(roomDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.service.RoomService.deleteRoom(String)\" because"
                        + " \"this.roomService\" is null",
                (new RoomCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).deleteRoom("Room Name"));
    }
}

