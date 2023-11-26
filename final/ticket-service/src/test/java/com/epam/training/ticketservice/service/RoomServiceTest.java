package com.epam.training.ticketservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.repository.RoomRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoomService.class})
@ExtendWith(SpringExtension.class)
class RoomServiceTest {
    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;



    /**
     * Method under test: {@link RoomService#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom2() throws Exception {
        doNothing().when(roomRepository).createRoom((Room) any());
        roomService.createRoom("Room Name", 1, 1);
        verify(roomRepository).createRoom((Room) any());
    }

    /**
     * Method under test: {@link RoomService#createRoom(String, int, int)}
     */
    @Test
    void testCreateRoom3() throws Exception {
        doThrow(new Exception()).when(roomRepository).createRoom((Room) any());
        assertThrows(Exception.class, () -> roomService.createRoom("Room Name", 1, 1));
        verify(roomRepository).createRoom((Room) any());
    }



    /**
     * Method under test: {@link RoomService#getAllRooms()}
     */
    @Test
    void testGetAllRooms2() {
        ArrayList<Room> roomList = new ArrayList<>();
        when(roomRepository.getAllRooms()).thenReturn(roomList);
        ArrayList<Room> actualAllRooms = roomService.getAllRooms();
        assertSame(roomList, actualAllRooms);
        assertTrue(actualAllRooms.isEmpty());
        verify(roomRepository).getAllRooms();
    }



    /**
     * Method under test: {@link RoomService#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom2() throws Exception {
        doNothing().when(roomRepository).updateRoomByRoom((Room) any());
        roomService.updateRoom("Room Name", 1, 1);
        verify(roomRepository).updateRoomByRoom((Room) any());
    }

    /**
     * Method under test: {@link RoomService#updateRoom(String, int, int)}
     */
    @Test
    void testUpdateRoom3() throws Exception {
        doThrow(new Exception()).when(roomRepository).updateRoomByRoom((Room) any());
        assertThrows(Exception.class, () -> roomService.updateRoom("Room Name", 1, 1));
        verify(roomRepository).updateRoomByRoom((Room) any());
    }


    /**
     * Method under test: {@link RoomService#deleteRoom(String)}
     */
    @Test
    void testDeleteRoom2() throws Exception {
        doNothing().when(roomRepository).deleteRoomByRoomName((String) any());
        roomService.deleteRoom("Room Name");
        verify(roomRepository).deleteRoomByRoomName((String) any());
    }

    /**
     * Method under test: {@link RoomService#deleteRoom(String)}
     */
    @Test
    void testDeleteRoom3() throws Exception {
        doThrow(new Exception()).when(roomRepository).deleteRoomByRoomName((String) any());
        assertThrows(Exception.class, () -> roomService.deleteRoom("Room Name"));
        verify(roomRepository).deleteRoomByRoomName((String) any());
    }
}

