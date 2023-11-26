package com.epam.training.ticketservice.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.domain.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JpaScreeningRepository.class})
@ExtendWith(SpringExtension.class)
class JpaScreeningRepositoryTest {
    @Autowired
    private JpaScreeningRepository jpaScreeningRepository;

    @MockBean
    private MovieDao movieDao;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private ScreeningDao screeningDao;


    /**
     * Method under test: {@link JpaScreeningRepository#createScreening(String, String, Date)}
     */
    @Test
    void testCreateScreening2() throws Exception {
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
        Optional<ScreeningProjection> ofResult = Optional.of(screeningProjection1);
        when(screeningDao.save((ScreeningProjection) any())).thenReturn(screeningProjection);
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        when(screeningDao
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any())).thenReturn(ofResult);

        MovieProjection movieProjection2 = new MovieProjection();
        movieProjection2.setMovieGenre("Movie Genre");
        movieProjection2.setMovieId(1L);
        movieProjection2.setMovieLength(3);
        movieProjection2.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult1 = Optional.of(movieProjection2);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult1);

        RoomProjection roomProjection2 = new RoomProjection();
        roomProjection2.setRoomId(1L);
        roomProjection2.setRoomName("Room Name");
        roomProjection2.setSeatColumns(1);
        roomProjection2.setSeatRows(1);
        Optional<RoomProjection> ofResult2 = Optional.of(roomProjection2);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult2);
        assertThrows(Exception.class, () -> jpaScreeningRepository.createScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(screeningDao)
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
        verify(movieDao).findByMovieTitle((String) any());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaScreeningRepository#createScreening(String, String, Date)}
     */
    @Test
    void testCreateScreening3() throws Exception {
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
        when(screeningDao.save((ScreeningProjection) any())).thenReturn(screeningProjection);
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        when(screeningDao
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any())).thenReturn(Optional.empty());

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection1);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(1L);
        roomProjection1.setRoomName("Room Name");
        roomProjection1.setSeatColumns(1);
        roomProjection1.setSeatRows(1);
        Optional<RoomProjection> ofResult1 = Optional.of(roomProjection1);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult1);
        jpaScreeningRepository.createScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(screeningDao).save((ScreeningProjection) any());
        verify(screeningDao).findAll();
        verify(screeningDao)
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
        verify(movieDao).findByMovieTitle((String) any());
        verify(roomDao).findByRoomName((String) any());
    }

    /**
     * Method under test: {@link JpaScreeningRepository#createScreening(String, String, Date)}
     */
    @Test
    void testCreateScreening4() throws Exception {
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
        Optional<ScreeningProjection> ofResult = Optional.of(screeningProjection1);
        when(screeningDao.save((ScreeningProjection) any())).thenReturn(screeningProjection);
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        when(screeningDao
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any())).thenReturn(ofResult);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());

        RoomProjection roomProjection2 = new RoomProjection();
        roomProjection2.setRoomId(1L);
        roomProjection2.setRoomName("Room Name");
        roomProjection2.setSeatColumns(1);
        roomProjection2.setSeatRows(1);
        Optional<RoomProjection> ofResult1 = Optional.of(roomProjection2);
        when(roomDao.findByRoomName((String) any())).thenReturn(ofResult1);
        assertThrows(Exception.class, () -> jpaScreeningRepository.createScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(movieDao).findByMovieTitle((String) any());
    }


    /**
     * Method under test: {@link JpaScreeningRepository#getAllScreenings()}
     */
    @Test
    void testGetAllScreenings2() {
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(jpaScreeningRepository.getAllScreenings().isEmpty());
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#getAllScreenings()}
     */
    @Test
    void testGetAllScreenings3() {
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
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        screeningCompositeKey.setScreeningTime(fromResult);

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        ArrayList<Screening> actualAllScreenings = jpaScreeningRepository.getAllScreenings();
        assertEquals(1, actualAllScreenings.size());
        Screening getResult = actualAllScreenings.get(0);
        assertSame(fromResult, getResult.getScreeningTime());
        Movie movie = getResult.getMovie();
        assertEquals(3, movie.getMovieLength());
        Room room = getResult.getRoom();
        assertEquals(1, room.getSeatRows());
        assertEquals(1, room.getSeatColumns());
        assertEquals("Room Name", room.getRoomName());
        assertEquals("Movie Genre", movie.getMovieGenre());
        assertEquals("Dr", movie.getMovieTitle());
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#getAllScreenings()}
     */
    @Test
    void testGetAllScreenings4() {
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
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        screeningCompositeKey.setScreeningTime(fromResult);

        ScreeningProjection screeningProjection = new ScreeningProjection();
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("com.epam.training.ticketservice.dataaccess.projection.MovieProjection");
        movieProjection1.setMovieId(2L);
        movieProjection1.setMovieLength(1);
        movieProjection1.setMovieTitle("Mr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(2L);
        roomProjection1.setRoomName("com.epam.training.ticketservice.dataaccess.projection.RoomProjection");
        roomProjection1.setSeatColumns(0);
        roomProjection1.setSeatRows(0);

        ScreeningCompositeKey screeningCompositeKey1 = new ScreeningCompositeKey();
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        Date fromResult1 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        screeningCompositeKey1.setScreeningTime(fromResult1);

        ScreeningProjection screeningProjection1 = new ScreeningProjection();
        screeningProjection1.setScreeningCompositeKey(screeningCompositeKey1);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection1);
        screeningProjectionList.add(screeningProjection);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        ArrayList<Screening> actualAllScreenings = jpaScreeningRepository.getAllScreenings();
        assertEquals(2, actualAllScreenings.size());
        Screening getResult = actualAllScreenings.get(0);
        assertSame(fromResult1, getResult.getScreeningTime());
        Screening getResult1 = actualAllScreenings.get(1);
        assertSame(fromResult, getResult1.getScreeningTime());
        Movie movie = getResult.getMovie();
        assertEquals("Mr", movie.getMovieTitle());
        Room room = getResult.getRoom();
        assertEquals(0, room.getSeatRows());
        assertEquals(0, room.getSeatColumns());
        assertEquals("com.epam.training.ticketservice.dataaccess.projection.RoomProjection", room.getRoomName());
        assertEquals(1, movie.getMovieLength());
        assertEquals("com.epam.training.ticketservice.dataaccess.projection.MovieProjection", movie.getMovieGenre());
        Movie movie1 = getResult1.getMovie();
        assertEquals(3, movie1.getMovieLength());
        assertEquals("Movie Genre", movie1.getMovieGenre());
        Room room1 = getResult1.getRoom();
        assertEquals(1, room1.getSeatColumns());
        assertEquals("Room Name", room1.getRoomName());
        assertEquals("Dr", movie1.getMovieTitle());
        assertEquals(1, room1.getSeatRows());
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#deleteScreening(String, String, Date)}
     */
    @Test
    void testDeleteScreening2() throws Exception {
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
        Optional<ScreeningProjection> ofResult = Optional.of(screeningProjection);
        doNothing().when(screeningDao)
                .deleteByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
        when(screeningDao
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any())).thenReturn(ofResult);
        jpaScreeningRepository.deleteScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(screeningDao)
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
        verify(screeningDao)
                .deleteByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
    }

    /**
     * Method under test: {@link JpaScreeningRepository#deleteScreening(String, String, Date)}
     */
    @Test
    void testDeleteScreening3() throws Exception {
        doNothing().when(screeningDao)
                .deleteByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
        when(screeningDao
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaScreeningRepository.deleteScreening("Dr", "Room Name",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(screeningDao)
                .findByScreeningCompositeKey_MovieProjection_MovieTitleAndScreeningCompositeKey_RoomProjection_RoomNameAndScreeningCompositeKey_ScreeningTime(
                        (String) any(), (String) any(), (Date) any());
    }


    /**
     * Method under test: {@link JpaScreeningRepository#getScreeningInterval(Screening)}
     */
    @Test
    void testGetScreeningInterval2() {
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertEquals(2, jpaScreeningRepository.getScreeningInterval(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))).length);
    }



    /**
     * Method under test: {@link JpaScreeningRepository#getScreeningInterval(Screening)}
     */
    @Test
    void testGetScreeningInterval4() {
        Movie movie = mock(Movie.class);
        when(movie.getMovieLength()).thenReturn(3);
        Room room = new Room("Room Name", 1, 1);

        assertEquals(2, jpaScreeningRepository.getScreeningInterval(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))).length);
        verify(movie).getMovieLength();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#canCreateScreening(Screening, int)}
     */
    @Test
    void testCanCreateScreening2() throws Exception {
        when(screeningDao.findAll()).thenReturn(new ArrayList<>());
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertTrue(jpaScreeningRepository.canCreateScreening(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1));
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#canCreateScreening(Screening, int)}
     */
    @Test
    void testCanCreateScreening3() throws Exception {
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

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertTrue(jpaScreeningRepository.canCreateScreening(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1));
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#canCreateScreening(Screening, int)}
     */
    @Test
    void testCanCreateScreening4() throws Exception {
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
        movieProjection1.setMovieId(2L);
        movieProjection1.setMovieLength(1);
        movieProjection1.setMovieTitle("Mr");

        RoomProjection roomProjection1 = new RoomProjection();
        roomProjection1.setRoomId(2L);
        roomProjection1.setRoomName("com.epam.training.ticketservice.dataaccess.projection.RoomProjection");
        roomProjection1.setSeatColumns(2);
        roomProjection1.setSeatRows(2);

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
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertTrue(jpaScreeningRepository.canCreateScreening(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1));
        verify(screeningDao).findAll();
    }

    /**
     * Method under test: {@link JpaScreeningRepository#canCreateScreening(Screening, int)}
     */
    @Test
    void testCanCreateScreening5() throws Exception {
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

        MovieProjection movieProjection2 = new MovieProjection();
        movieProjection2.setMovieGenre("Movie Genre");
        movieProjection2.setMovieId(1L);
        movieProjection2.setMovieLength(3);
        movieProjection2.setMovieTitle("Dr");

        RoomProjection roomProjection2 = new RoomProjection();
        roomProjection2.setRoomId(1L);
        roomProjection2.setRoomName("Room Name");
        roomProjection2.setSeatColumns(1);
        roomProjection2.setSeatRows(1);
        ScreeningCompositeKey screeningCompositeKey1 = mock(ScreeningCompositeKey.class);
        when(screeningCompositeKey1.getScreeningTime())
                .thenReturn(Date.from(LocalDate.of(1, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(screeningCompositeKey1.getRoomProjection()).thenReturn(roomProjection2);
        when(screeningCompositeKey1.getMovieProjection()).thenReturn(movieProjection2);
        doNothing().when(screeningCompositeKey1).setMovieProjection((MovieProjection) any());
        doNothing().when(screeningCompositeKey1).setRoomProjection((RoomProjection) any());
        doNothing().when(screeningCompositeKey1).setScreeningTime((Date) any());
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ScreeningProjection screeningProjection = mock(ScreeningProjection.class);
        when(screeningProjection.getScreeningCompositeKey()).thenReturn(screeningCompositeKey1);
        doNothing().when(screeningProjection).setScreeningCompositeKey((ScreeningCompositeKey) any());
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertTrue(jpaScreeningRepository.canCreateScreening(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1));
        verify(screeningDao).findAll();
        verify(screeningProjection, atLeast(1)).getScreeningCompositeKey();
        verify(screeningProjection).setScreeningCompositeKey((ScreeningCompositeKey) any());
        verify(screeningCompositeKey1).getMovieProjection();
        verify(screeningCompositeKey1).getRoomProjection();
        verify(screeningCompositeKey1).getScreeningTime();
        verify(screeningCompositeKey1).setMovieProjection((MovieProjection) any());
        verify(screeningCompositeKey1).setRoomProjection((RoomProjection) any());
        verify(screeningCompositeKey1).setScreeningTime((Date) any());
    }

    /**
     * Method under test: {@link JpaScreeningRepository#canCreateScreening(Screening, int)}
     */
    @Test
    void testCanCreateScreening6() throws Exception {
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

        MovieProjection movieProjection2 = new MovieProjection();
        movieProjection2.setMovieGenre("Movie Genre");
        movieProjection2.setMovieId(1L);
        movieProjection2.setMovieLength(3);
        movieProjection2.setMovieTitle("Dr");

        RoomProjection roomProjection2 = new RoomProjection();
        roomProjection2.setRoomId(1L);
        roomProjection2.setRoomName("Room Name");
        roomProjection2.setSeatColumns(1);
        roomProjection2.setSeatRows(1);
        ScreeningCompositeKey screeningCompositeKey1 = mock(ScreeningCompositeKey.class);
        when(screeningCompositeKey1.getScreeningTime())
                .thenReturn(Date.from(LocalDate.ofEpochDay(1L).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(screeningCompositeKey1.getRoomProjection()).thenReturn(roomProjection2);
        when(screeningCompositeKey1.getMovieProjection()).thenReturn(movieProjection2);
        doNothing().when(screeningCompositeKey1).setMovieProjection((MovieProjection) any());
        doNothing().when(screeningCompositeKey1).setRoomProjection((RoomProjection) any());
        doNothing().when(screeningCompositeKey1).setScreeningTime((Date) any());
        screeningCompositeKey1.setMovieProjection(movieProjection1);
        screeningCompositeKey1.setRoomProjection(roomProjection1);
        screeningCompositeKey1
                .setScreeningTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ScreeningProjection screeningProjection = mock(ScreeningProjection.class);
        when(screeningProjection.getScreeningCompositeKey()).thenReturn(screeningCompositeKey1);
        doNothing().when(screeningProjection).setScreeningCompositeKey((ScreeningCompositeKey) any());
        screeningProjection.setScreeningCompositeKey(screeningCompositeKey);

        ArrayList<ScreeningProjection> screeningProjectionList = new ArrayList<>();
        screeningProjectionList.add(screeningProjection);
        when(screeningDao.findAll()).thenReturn(screeningProjectionList);
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertTrue(jpaScreeningRepository.canCreateScreening(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1));
        verify(screeningDao).findAll();
        verify(screeningProjection, atLeast(1)).getScreeningCompositeKey();
        verify(screeningProjection).setScreeningCompositeKey((ScreeningCompositeKey) any());
        verify(screeningCompositeKey1).getMovieProjection();
        verify(screeningCompositeKey1).getRoomProjection();
        verify(screeningCompositeKey1).getScreeningTime();
        verify(screeningCompositeKey1).setMovieProjection((MovieProjection) any());
        verify(screeningCompositeKey1).setRoomProjection((RoomProjection) any());
        verify(screeningCompositeKey1).setScreeningTime((Date) any());
    }


    /**
     * Method under test: {@link JpaScreeningRepository#getBreakPeriod(Screening, int)}
     */
    @Test
    void testGetBreakPeriod2() {
        Movie movie = new Movie("Dr", "Movie Genre", 3);

        Room room = new Room("Room Name", 1, 1);

        assertEquals(2, jpaScreeningRepository.getBreakPeriod(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1).length);
    }


    /**
     * Method under test: {@link JpaScreeningRepository#getBreakPeriod(Screening, int)}
     */
    @Test
    void testGetBreakPeriod4() {
        Movie movie = mock(Movie.class);
        when(movie.getMovieLength()).thenReturn(3);
        Room room = new Room("Room Name", 1, 1);

        assertEquals(2, jpaScreeningRepository.getBreakPeriod(new Screening(movie, room,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())), 1).length);
        verify(movie, atLeast(1)).getMovieLength();
    }


}

