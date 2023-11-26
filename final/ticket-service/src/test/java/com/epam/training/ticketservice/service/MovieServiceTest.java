package com.epam.training.ticketservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MovieService.class})
@ExtendWith(SpringExtension.class)
class MovieServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;



    /**
     * Method under test: {@link MovieService#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie2() throws Exception {
        doNothing().when(movieRepository).createMovie((Movie) any());
        movieService.createMovie("Dr", "Movie Genre", 3);
        verify(movieRepository).createMovie((Movie) any());
    }

    /**
     * Method under test: {@link MovieService#createMovie(String, String, int)}
     */
    @Test
    void testCreateMovie3() throws Exception {
        doThrow(new Exception()).when(movieRepository).createMovie((Movie) any());
        assertThrows(Exception.class, () -> movieService.createMovie("Dr", "Movie Genre", 3));
        verify(movieRepository).createMovie((Movie) any());
    }


    /**
     * Method under test: {@link MovieService#getAllMovies()}
     */
    @Test
    void testGetAllMovies2() {
        ArrayList<Movie> movieList = new ArrayList<>();
        when(movieRepository.getAllMovies()).thenReturn(movieList);
        ArrayList<Movie> actualAllMovies = movieService.getAllMovies();
        assertSame(movieList, actualAllMovies);
        assertTrue(actualAllMovies.isEmpty());
        verify(movieRepository).getAllMovies();
    }


    /**
     * Method under test: {@link MovieService#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie2() throws Exception {
        doNothing().when(movieRepository).updateMovieByMovie((Movie) any());
        movieService.updateMovie("Dr", "Movie Genre", 3);
        verify(movieRepository).updateMovieByMovie((Movie) any());
    }

    /**
     * Method under test: {@link MovieService#updateMovie(String, String, int)}
     */
    @Test
    void testUpdateMovie3() throws Exception {
        doThrow(new Exception()).when(movieRepository).updateMovieByMovie((Movie) any());
        assertThrows(Exception.class, () -> movieService.updateMovie("Dr", "Movie Genre", 3));
        verify(movieRepository).updateMovieByMovie((Movie) any());
    }


    /**
     * Method under test: {@link MovieService#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie2() throws Exception {
        doNothing().when(movieRepository).deleteMovieByMovieTitle((String) any());
        movieService.deleteMovie("Dr");
        verify(movieRepository).deleteMovieByMovieTitle((String) any());
    }

    /**
     * Method under test: {@link MovieService#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie3() throws Exception {
        doThrow(new Exception()).when(movieRepository).deleteMovieByMovieTitle((String) any());
        assertThrows(Exception.class, () -> movieService.deleteMovie("Dr"));
        verify(movieRepository).deleteMovieByMovieTitle((String) any());
    }
}

