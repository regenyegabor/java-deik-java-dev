package com.epam.training.ticketservice.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.domain.Room;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JpaRoomRepository.class})
@ExtendWith(SpringExtension.class)
class JpaRoomRepositoryTest {
    @Autowired
    private JpaRoomRepository jpaRoomRepository;

    @MockBean
    private RoomDao roomDao;


    /**
     * Method under test: {@link JpaRoomRepository#createRoom(Room)}
     */
    @Test
    void testCreateRoom2() throws Exception {
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
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        assertThrows(Exception.class, () -> jpaRoomRepository.createRoom(new Room("Room Name", 1, 1)));
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaRoomRepository#createRoom(Room)}
     */
    @Test
    void testCreateRoom3() throws Exception {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        jpaRoomRepository.createRoom(new Room("Room Name", 1, 1));
        verify(roomDao).save((RoomProjection) any());
        verify(roomDao).findByRoomName((String) any());
    }


    /**
     * Method under test: {@link JpaRoomRepository#getAllRooms()}
     */
    @Test
    void testGetAllRooms2() {
        when(roomDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(jpaRoomRepository.getAllRooms().isEmpty());
        verify(roomDao).findAll();
    }

    /**
     * Method under test: {@link JpaRoomRepository#getAllRooms()}
     */
    @Test
    void testGetAllRooms3() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ArrayList<RoomProjection> roomProjectionList = new ArrayList<>();
        roomProjectionList.add(roomProjection);
        when(roomDao.findAll()).thenReturn(roomProjectionList);
        ArrayList<Room> actualAllRooms = jpaRoomRepository.getAllRooms();
        assertEquals(1, actualAllRooms.size());
        Room getResult = actualAllRooms.get(0);
        assertEquals("Room Name", getResult.getRoomName());
        assertEquals(1, getResult.getSeatRows());
        assertEquals(1, getResult.getSeatColumns());
        verify(roomDao).findAll();
    }

    /**
     * Method under test: {@link JpaRoomRepository#getAllRooms()}
     */
    @Test
    void testGetAllRooms4() {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(2L);
        roomProjection1.setRoomName("com.epam.training.ticketservice.dataaccess.projection.RoomProjection");
        roomProjection1.setSeatColumns(0);
        roomProjection1.setSeatRows(0);

        ArrayList<RoomProjection> roomProjectionList = new ArrayList<>();
        roomProjectionList.add(roomProjection1);
        roomProjectionList.add(roomProjection);
        when(roomDao.findAll()).thenReturn(roomProjectionList);
        ArrayList<Room> actualAllRooms = jpaRoomRepository.getAllRooms();
        assertEquals(2, actualAllRooms.size());
        Room getResult = actualAllRooms.get(0);
        assertEquals(0, getResult.getSeatRows());
        Room getResult1 = actualAllRooms.get(1);
        assertEquals(1, getResult1.getSeatRows());
        assertEquals(1, getResult1.getSeatColumns());
        assertEquals("Room Name", getResult1.getRoomName());
        assertEquals(0, getResult.getSeatColumns());
        assertEquals("com.epam.training.ticketservice.dataaccess.projection.RoomProjection", getResult.getRoomName());
        verify(roomDao).findAll();
    }


    /**
     * Method under test: {@link JpaRoomRepository#findRoomByRoomName(String)}
     */
    @Test
    void testFindRoomByRoomName2() throws Exception {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        Room actualFindRoomByRoomNameResult = jpaRoomRepository.findRoomByRoomName("Room Name");
        assertEquals("Room Name", actualFindRoomByRoomNameResult.getRoomName());
        assertEquals(1, actualFindRoomByRoomNameResult.getSeatRows());
        assertEquals(1, actualFindRoomByRoomNameResult.getSeatColumns());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaRoomRepository#findRoomByRoomName(String)}
     */
    @Test
    void testFindRoomByRoomName3() throws Exception {
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaRoomRepository.findRoomByRoomName("Room Name"));
        verify(roomDao).findByRoomName((String) any());
    }


    /**
     * Method under test: {@link JpaRoomRepository#updateRoomByRoom(Room)}
     */
    @Test
    void testUpdateRoomByRoom2() throws Exception {
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
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection1);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        jpaRoomRepository.updateRoomByRoom(new Room("Room Name", 1, 1));
        verify(roomDao).save((RoomProjection) any());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaRoomRepository#updateRoomByRoom(Room)}
     */
    @Test
    void testUpdateRoomByRoom3() throws Exception {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        when(roomDao.save((RoomProjection) any())).thenReturn(roomProjection);
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaRoomRepository.updateRoomByRoom(new Room("Room Name", 1, 1)));
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaRoomRepository#deleteRoomByRoomName(String)}
     */
    @Test
    void testDeleteRoomByRoomName2() throws Exception {
        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("Room Name");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);
        Optional<RoomProjection> ofResult = Optional.of(roomProjection);
        doNothing().when(roomDao).deleteByRoomName((String) any());
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult);
        jpaRoomRepository.deleteRoomByRoomName("Room Name");
        verify(roomDao).findByRoomName((String) any());
        verify(roomDao).deleteByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaRoomRepository#deleteRoomByRoomName(String)}
     */
    @Test
    void testDeleteRoomByRoomName3() throws Exception {
        doNothing().when(roomDao).deleteByRoomName((String) any());
        when(roomDao.findByRoomName((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaRoomRepository.deleteRoomByRoomName("Room Name"));
        verify(roomDao).findByRoomName((String) any());
    }
}

