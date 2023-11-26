package com.epam.training.ticketservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.domain.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScreeningService.class})
@ExtendWith(SpringExtension.class)
class ScreeningServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningService screeningService;


    /**
     * Method under test: {@link ScreeningService#createScreening(String, String, Date)}
     */
    @Test
    void testCreateScreening2() throws Exception {
        doNothing().when(screeningRepository).createScreening((String) any(), (String) any(), (Date) any());
        screeningService.createScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(screeningRepository).createScreening((String) any(), (String) any(), (Date) any());
    }

    /**
     * Method under test: {@link ScreeningService#createScreening(String, String, Date)}
     */
    @Test
    void testCreateScreening3() throws Exception {
        doThrow(new Exception()).when(screeningRepository).createScreening((String) any(), (String) any(), (Date) any());
        assertThrows(Exception.class, () -> screeningService.createScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(screeningRepository).createScreening((String) any(), (String) any(), (Date) any());
    }



    /**
     * Method under test: {@link ScreeningService#getAllScreenings()}
     */
    @Test
    void testGetAllScreenings2() {
        ArrayList<Screening> screeningList = new ArrayList<>();
        when(screeningRepository.getAllScreenings()).thenReturn(screeningList);
        ArrayList<Screening> actualAllScreenings = screeningService.getAllScreenings();
        assertSame(screeningList, actualAllScreenings);
        assertTrue(actualAllScreenings.isEmpty());
        verify(screeningRepository).getAllScreenings();
    }


    /**
     * Method under test: {@link ScreeningService#deleteScreening(String, String, Date)}
     */
    @Test
    void testDeleteScreening2() throws Exception {
        doNothing().when(screeningRepository).deleteScreening((String) any(), (String) any(), (Date) any());
        screeningService.deleteScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(screeningRepository).deleteScreening((String) any(), (String) any(), (Date) any());
    }

    /**
     * Method under test: {@link ScreeningService#deleteScreening(String, String, Date)}
     */
    @Test
    void testDeleteScreening3() throws Exception {
        doThrow(new Exception()).when(screeningRepository).deleteScreening((String) any(), (String) any(), (Date) any());
        assertThrows(Exception.class, () -> screeningService.deleteScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(screeningRepository).deleteScreening((String) any(), (String) any(), (Date) any());
    }
}

