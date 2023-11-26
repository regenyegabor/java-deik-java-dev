package com.epam.training.ticketservice.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.domain.Movie;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JpaMovieRepository.class})
@ExtendWith(SpringExtension.class)
class JpaMovieRepositoryTest {
    @Autowired
    private JpaMovieRepository jpaMovieRepository;

    @MockBean
    private MovieDao movieDao;



    /**
     * Method under test: {@link JpaMovieRepository#createMovie(Movie)}
     */
    @Test
    void testCreateMovie2() throws Exception {
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
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        assertThrows(Exception.class, () -> jpaMovieRepository.createMovie(new Movie("Dr", "Movie Genre", 3)));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#createMovie(Movie)}
     */
    @Test
    void testCreateMovie3() throws Exception {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        jpaMovieRepository.createMovie(new Movie("Dr", "Movie Genre", 3));
        verify(movieDao).save((MovieProjection) any());
        verify(movieDao).findByMovieTitle((String) any());
    }


    /**
     * Method under test: {@link JpaMovieRepository#getAllMovies()}
     */
    @Test
    void testGetAllMovies2() {
        when(movieDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(jpaMovieRepository.getAllMovies().isEmpty());
        verify(movieDao).findAll();
    }

    /**
     * Method under test: {@link JpaMovieRepository#getAllMovies()}
     */
    @Test
    void testGetAllMovies3() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        ArrayList<MovieProjection> movieProjectionList = new ArrayList<>();
        movieProjectionList.add(movieProjection);
        when(movieDao.findAll()).thenReturn(movieProjectionList);
        ArrayList<Movie> actualAllMovies = jpaMovieRepository.getAllMovies();
        assertEquals(1, actualAllMovies.size());
        Movie getResult = actualAllMovies.get(0);
        assertEquals("Movie Genre", getResult.getMovieGenre());
        assertEquals("Dr", getResult.getMovieTitle());
        assertEquals(3, getResult.getMovieLength());
        verify(movieDao).findAll();
    }

    /**
     * Method under test: {@link JpaMovieRepository#getAllMovies()}
     */
    @Test
    void testGetAllMovies4() {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");

        MovieProjection movieProjection1 = new MovieProjection();
        movieProjection1.setMovieGenre("com.epam.training.ticketservice.dataaccess.projection.MovieProjection");
        movieProjection1.setMovieId(2L);
        movieProjection1.setMovieLength(1);
        movieProjection1.setMovieTitle("Mr");

        ArrayList<MovieProjection> movieProjectionList = new ArrayList<>();
        movieProjectionList.add(movieProjection1);
        movieProjectionList.add(movieProjection);
        when(movieDao.findAll()).thenReturn(movieProjectionList);
        ArrayList<Movie> actualAllMovies = jpaMovieRepository.getAllMovies();
        assertEquals(2, actualAllMovies.size());
        Movie getResult = actualAllMovies.get(0);
        assertEquals("Mr", getResult.getMovieTitle());
        Movie getResult1 = actualAllMovies.get(1);
        assertEquals("Dr", getResult1.getMovieTitle());
        assertEquals(3, getResult1.getMovieLength());
        assertEquals("Movie Genre", getResult1.getMovieGenre());
        assertEquals(1, getResult.getMovieLength());
        assertEquals("com.epam.training.ticketservice.dataaccess.projection.MovieProjection", getResult.getMovieGenre());
        verify(movieDao).findAll();
    }

    /**
     * Method under test: {@link JpaMovieRepository#findMovieByMovieTitle(String)}
     */
    @Test
    void testFindMovieByMovieTitle2() throws Exception {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        Movie actualFindMovieByMovieTitleResult = jpaMovieRepository.findMovieByMovieTitle("Dr");
        assertEquals("Movie Genre", actualFindMovieByMovieTitleResult.getMovieGenre());
        assertEquals("Dr", actualFindMovieByMovieTitleResult.getMovieTitle());
        assertEquals(3, actualFindMovieByMovieTitleResult.getMovieLength());
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#findMovieByMovieTitle(String)}
     */
    @Test
    void testFindMovieByMovieTitle3() throws Exception {
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaMovieRepository.findMovieByMovieTitle("Dr"));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#updateMovieByMovie(Movie)}
     */
    @Test
    void testUpdateMovieByMovie2() throws Exception {
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
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection1);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        jpaMovieRepository.updateMovieByMovie(new Movie("Dr", "Movie Genre", 3));
        verify(movieDao).save((MovieProjection) any());
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#updateMovieByMovie(Movie)}
     */
    @Test
    void testUpdateMovieByMovie3() throws Exception {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        when(movieDao.save((MovieProjection) any())).thenReturn(movieProjection);
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaMovieRepository.updateMovieByMovie(new Movie("Dr", "Movie Genre", 3)));
        verify(movieDao).findByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#deleteMovieByMovieTitle(String)}
     */
    @Test
    void testDeleteMovieByMovieTitle2() throws Exception {
        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovieGenre("Movie Genre");
        movieProjection.setMovieId(1L);
        movieProjection.setMovieLength(3);
        movieProjection.setMovieTitle("Dr");
        Optional<MovieProjection> ofResult = Optional.of(movieProjection);
        doNothing().when(movieDao).deleteByMovieTitle((String) any());
        when(movieDao.findByMovieTitle((String) any())).thenReturn(ofResult);
        jpaMovieRepository.deleteMovieByMovieTitle("Dr");
        verify(movieDao).findByMovieTitle((String) any());
        verify(movieDao).deleteByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link JpaMovieRepository#deleteMovieByMovieTitle(String)}
     */
    @Test
    void testDeleteMovieByMovieTitle3() throws Exception {
        doNothing().when(movieDao).deleteByMovieTitle((String) any());
        when(movieDao.findByMovieTitle((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaMovieRepository.deleteMovieByMovieTitle("Dr"));
        verify(movieDao).findByMovieTitle((String) any());
    }
}

