package com.epam.training.ticketservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    /**
     * Method under test: {@link UserService#createUser(String, String, boolean)}
     */
    @Test
    void testCreateUser2() throws Exception {
        doNothing().when(userRepository).createUser((User) any());
        userService.createUser("janedoe", "iloveyou", true);
        verify(userRepository).createUser((User) any());
    }

    /**
     * Method under test: {@link UserService#createUser(String, String, boolean)}
     */
    @Test
    void testCreateUser3() throws Exception {
        doThrow(new Exception()).when(userRepository).createUser((User) any());
        assertThrows(Exception.class, () -> userService.createUser("janedoe", "iloveyou", true));
        verify(userRepository).createUser((User) any());
    }
}

