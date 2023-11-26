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

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.impl.JpaMovieRepository;
import com.epam.training.ticketservice.security.session.SessionManager;
import com.epam.training.ticketservice.security.session.SessionManagerImplementation;
import com.epam.training.ticketservice.security.session.TokenCollector;
import com.epam.training.ticketservice.security.session.TokenCollectorImplementation;
import com.epam.training.ticketservice.service.MovieService;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Availability;

class MovieCommandHandlerTest {


    /**
     * Method under test: {@link MovieCommandHandler#MovieCommandHandler(SessionManager, TokenCollector, MovieService)}
     */
    @Test
    void testConstructor2() {
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        TokenCollectorImplementation tokenCollector = new TokenCollectorImplementation();
        MovieCommandHandler actualMovieCommandHandler = new MovieCommandHandler(sessionManager, tokenCollector,
                new MovieService(new JpaMovieRepository(mock(MovieDao.class))));

        Availability isUserSignedInResult = actualMovieCommandHandler.isUserSignedIn();
        assertFalse(isUserSignedInResult.isAvailable());
        Availability isUserPrivilegedResult = actualMovieCommandHandler.isUserPrivileged();
        assertFalse(isUserPrivilegedResult.isAvailable());
        assertEquals("Please sign in to be able to use this command!", isUserPrivilegedResult.getReason());
        assertEquals("Please sign in to be able to use this command!", isUserSignedInResult.getReason());
        Availability isUserNotSignedInResult = actualMovieCommandHandler.isUserNotSignedIn();
        assertNull(isUserNotSignedInResult.getReason());
        assertTrue(isUserNotSignedInResult.isAvailable());
    }


    /**
     * Method under test: {@link MovieCommandHandler#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie2() {
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
        Optional<MovieProjection> ofResult = Optional.of(movieProjection1);
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Movie with title 'Dr' is already exists!",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService)).createMovie("Dr",
                        "Movie Genre", 3));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                .createMovie("Dr", "Movie Genre", 3));
        verify(movieDao).save((MovieProjection) any());
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie4() {
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
        Optional<MovieProjection> ofResult = Optional.of(movieProjection1);
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        new JpaMovieRepository(movieDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.service.MovieService.createMovie(String, String, int)\""
                        + " because \"this.movieService\" is null",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).createMovie("Dr",
                        "Movie Genre", 3));
    }

    /**
     * Method under test: {@link MovieCommandHandler#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie5() throws Exception {
        JpaMovieRepository jpaMovieRepository = mock(JpaMovieRepository.class);
        doNothing().when(jpaMovieRepository).createMovie((Movie) any());
        MovieService movieService = new MovieService(jpaMovieRepository);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                .createMovie("foo", "foo", 1));
        verify(jpaMovieRepository).createMovie((Movie) any());
    }



    /**
     * Method under test: {@link MovieCommandHandler#listMovies()}
     */
    @Test
    void testListMovies2() {
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll()).thenReturn(new ArrayList<>());
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("There are no movies at the moment",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService)).listMovies());
        verify(movieDao).findAll();
    }

    /**
     * Method under test: {@link MovieCommandHandler#listMovies()}
     */
    @Test
    void testListMovies3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("There are no movies at the moment");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        ArrayList<MovieProjection> movieProjectionList = new ArrayList<>();
        movieProjectionList.add(movieProjection);
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll()).thenReturn(movieProjectionList);
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Dr (there are no movies at the moment, 3 minutes)\n",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService)).listMovies());
        verify(movieDao).findAll();
    }

    /**
     * Method under test: {@link MovieCommandHandler#listMovies()}
     */
    @Test
    void testListMovies4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("There are no movies at the moment");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(2L);
        movieProjection1.setMovieLength(1);
        movieProjection1.setMovieTitle("Mr");

        ArrayList<MovieProjection> movieProjectionList = new ArrayList<>();
        movieProjectionList.add(movieProjection1);
        movieProjectionList.add(movieProjection);
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll()).thenReturn(movieProjectionList);
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Mr (movie genre, 1 minutes)\nDr (there are no movies at the moment, 3 minutes)\n",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService)).listMovies());
        verify(movieDao).findAll();
    }


    /**
     * Method under test: {@link MovieCommandHandler#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie2() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection1);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                .updateMovie("Dr", "Movie Genre", 3));
        verify(movieDao).save((MovieProjection) any());
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Movie not found with 'Dr' title!",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService)).updateMovie("Dr",
                        "Movie Genre", 3));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("Movie Genre");
        movieProjection1.setMovieId(1L);
        movieProjection1.setMovieLength(3);
        movieProjection1.setMovieTitle("Dr");
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection1);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        new JpaMovieRepository(movieDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.service.MovieService.updateMovie(String, String, int)\""
                        + " because \"this.movieService\" is null",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).updateMovie("Dr",
                        "Movie Genre", 3));
    }

    /**
     * Method under test: {@link MovieCommandHandler#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie5() throws Exception {
        JpaMovieRepository jpaMovieRepository = mock(JpaMovieRepository.class);
        doNothing().when(jpaMovieRepository).updateMovieByMovie((Movie) any());
        MovieService movieService = new MovieService(jpaMovieRepository);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(0, true, true);

        assertNull((new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                .updateMovie("Dr", "Movie Genre", 3));
        verify(jpaMovieRepository).updateMovieByMovie((Movie) any());
    }



    /**
     * Method under test: {@link MovieCommandHandler#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie2() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);
        MovieDao movieDao = mock(MovieDao.class);
        doNothing().when(movieDao).deleteByMovieTitle((String) any());
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertNull((new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                .deleteMovie("Dr"));
        verify(movieDao).findByMovieTitle((String) any());
        verify(movieDao).deleteByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie3() {
        MovieDao movieDao = mock(MovieDao.class);
        doNothing().when(movieDao).deleteByMovieTitle((String) any());
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        MovieService movieService = new MovieService(new JpaMovieRepository(movieDao));
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals("Movie not found with 'Dr' title!",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), movieService))
                        .deleteMovie("Dr"));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieCommandHandler#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);
        MovieDao movieDao = mock(MovieDao.class);
        doNothing().when(movieDao).deleteByMovieTitle((String) any());
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        new JpaMovieRepository(movieDao);
        SessionManagerImplementation sessionManager = new SessionManagerImplementation(1, true, true);

        assertEquals(
                "Cannot invoke \"com.epam.training.ticketservice.service.MovieService.deleteMovie(String)\" because"
                        + " \"this.movieService\" is null",
                (new MovieCommandHandler(sessionManager, new TokenCollectorImplementation(), null)).deleteMovie("Dr"));
    }
}

