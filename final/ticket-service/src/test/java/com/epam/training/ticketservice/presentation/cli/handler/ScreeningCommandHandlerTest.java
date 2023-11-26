package com.epam.training.ticketservice.presentation.cli.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.dao.ScreeningDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.dataaccess.projection.ScreeningProjection;
import com.epam.training.ticketservice.dataaccess.projection.compositekey.ScreeningCompositeKey;
import com.epam.training.ticketservice.repository.impl.JpaMovieRepository;
import com.epam.training.ticketservice.repository.impl.JpaRoomRepository;
import com.epam.training.ticketservice.repository.impl.JpaScreeningRepository;
import com.epam.training.ticketservice.security.session.SessionManager;
import com.epam.training.ticketservice.security.session.SessionManagerImplementation;
import com.epam.training.ticketservice.security.session.TokenCollector;
import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.utilities.converter.DateConverter;

import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Availability;

class ScreeningCommandHandlerTest {

    /**
     * Method under test: {@link ScreeningCommandHandler#ScreeningCommandHandler(SessionManager, TokenCollector, ScreeningService, DateConverter)}
     */
    @Test
    void testConstructor2() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        ScreeningCommandHandler actualScreeningCommandHandler = new ScreeningCommandHandler(sessionManager,
                tokenCollector, screeningService, new DateConverter());

        Availability isUserSignedInResult = actualScreeningCommandHandler.isUserSignedIn();
        assertFalse(isUserSignedInResult.isAvailable());
        Availability isUserPrivilegedResult = actualScreeningCommandHandler.isUserPrivileged();
        assertFalse(isUserPrivilegedResult.isAvailable());
        assertEquals("Please sign in to be able to use this command!", isUserPrivilegedResult.getReason());
        assertEquals("Please sign in to be able to use this command!", isUserSignedInResult.getReason());
        Availability isUserNotSignedInResult = actualScreeningCommandHandler.isUserNotSignedIn();
        assertNull(isUserNotSignedInResult.getReason());
        assertTrue(isUserNotSignedInResult.isAvailable());
    }


    /**
     * Method under test: {@link ScreeningCommandHandler#createScreening(String, String, String)}
     */
    @Test
    void testCreateScreening2() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        assertEquals("\u001b[31m Wrong date format! Use format 'yyyy-MM-dd HH:mm'!\u001b[0m",
                (new ScreeningCommandHandler(sessionManager, tokenCollector, screeningService, new DateConverter()))
                        .createScreening("Dr", "Room Name", "Screening Time"));
    }

    /**
     * Method under test: {@link ScreeningCommandHandler#createScreening(String, String, String)}
     */
    @Test
    void testCreateScreening3() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.utilities.converter.DateConverter.convertStringToDate(String)\""
                        + " because \"this.dateConverter\" is null",
                (new ScreeningCommandHandler(sessionManager, tokenCollector,
                        new ScreeningService(screeningRepository, movieRepository1, new JpaRoomRepository(mock(RoomDao.class))),
                        null)).createScreening("Dr", "Room Name", "Screening Time"));
    }


    /**
     * Method under test: {@link ScreeningCommandHandler#listScreenings()}
     */
    @Test
    void testListScreenings2() {
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        assertEquals("There are no screenings",
                (new ScreeningCommandHandler(sessionManager, tokenCollector, screeningService, new DateConverter()))
                        .listScreenings());
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link ScreeningCommandHandler#listScreenings()}
     */
    @Test
    void testListScreenings3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("There are no screenings");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("There are no screenings");
        roomProjection.setSeatColumns(1);
        roomProjection.setSeatRows(1);

        ScreeningCompositeKey screeningCompositeKey = new ScreeningCompositeKey();
        screeningCompositeKey.setMovieProjection(movieProjection);
        screeningCompositeKey.setRoomProjection(roomProjection);
        screeningCompositeKey
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection);
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        (new ScreeningCommandHandler(sessionManager, tokenCollector, screeningService, new DateConverter()))
                .listScreenings();
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link ScreeningCommandHandler#listScreenings()}
     */
    @Test
    void testListScreenings4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("There are no screenings");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        RoomProjection roomProjection = new RoomProjection();
        roomProjection.setRoomId(1L);
        roomProjection.setRoomName("There are no screenings");
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
        movieProjection1.setMovieGenre("\n");
        movieProjection1.setMovieId(2L);
        movieProjection1.setMovieLength(1);
        movieProjection1.setMovieTitle("Mr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(2L);
        roomProjection1.setRoomName("\n");
        roomProjection1.setSeatColumns(0);
        roomProjection1.setSeatRows(0);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey1);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection1);
        screeningProjectionList.add(screeningProjection);
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        (new ScreeningCommandHandler(sessionManager, tokenCollector, screeningService, new DateConverter()))
                .listScreenings();
        verify(screeningDao).findAll();
    }



    /**
     * Method under test: {@link ScreeningCommandHandler#deleteScreening(String, String, String)}
     */
    @Test
    void testDeleteScreening2() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        ScreeningService screeningService = new ScreeningService(screeningRepository, movieRepository1,
                new JpaRoomRepository(mock(RoomDao.class)));

        assertEquals("\u001b[31m Wrong date format! Use format 'yyyy-MM-dd HH:mm'!\u001b[0m",
                (new ScreeningCommandHandler(sessionManager, tokenCollector, screeningService, new DateConverter()))
                        .deleteScreening("Dr", "Room Name", "Screening Time"));
    }

    /**
     * Method under test: {@link ScreeningCommandHandler#deleteScreening(String, String, String)}
     */
    @Test
    void testDeleteScreening3() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        ScreeningDao screeningDao = mock(ScreeningDao.class);
        MovieDao movieDao = mock(MovieDao.class);
        RoomDao roomDao = mock(RoomDao.class);
        JpaMovieRepository movieRepository = new JpaMovieRepository(mock(MovieDao.class));
        JpaScreeningRepository screeningRepository = new JpaScreeningRepository(screeningDao, movieDao, roomDao,
                movieRepository, new JpaRoomRepository(mock(RoomDao.class)));

        JpaMovieRepository movieRepository1 = new JpaMovieRepository(mock(MovieDao.class));
        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.utilities.converter.DateConverter.convertStringToDate(String)\""
                        + " because \"this.dateConverter\" is null",
                (new ScreeningCommandHandler(sessionManager, tokenCollector,
                        new ScreeningService(screeningRepository, movieRepository1, new JpaRoomRepository(mock(RoomDao.class))),
                        null)).deleteScreening("Dr", "Room Name", "Screening Time"));
    }
}

