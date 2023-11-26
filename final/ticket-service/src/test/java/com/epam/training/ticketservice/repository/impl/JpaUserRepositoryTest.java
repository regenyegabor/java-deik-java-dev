package com.epam.training.ticketservice.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.domain.User;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JpaUserRepository.class})
@ExtendWith(SpringExtension.class)
class JpaUserRepositoryTest {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @MockBean
    private UserDao userDao;



    /**
     * Method under test: {@link JpaUserRepository#findUserByUserName(String)}
     */
    @Test
    void testFindUserByUserName2() throws Exception {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        Optional<UserProjection> ofResult = Optional.of(userProjection);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        User actualFindUserByUserNameResult = jpaUserRepository.findUserByUserName("janedoe");
        assertEquals("janedoe", actualFindUserByUserNameResult.getUserName());
        assertTrue(actualFindUserByUserNameResult.isPrivileged());
        assertEquals("iloveyou", actualFindUserByUserNameResult.getUserPassword());
        verify(userDao).findByUserName((String) any());
    }

    /**
     * Method under test: {@link JpaUserRepository#findUserByUserName(String)}
     */
    @Test
    void testFindUserByUserName3() throws Exception {
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jpaUserRepository.findUserByUserName("janedoe"));
        verify(userDao).findByUserName((String) any());
    }



    /**
     * Method under test: {@link JpaUserRepository#createUser(User)}
     */
    @Test
    void testCreateUser2() throws Exception {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(UUID.randomUUID());
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        Optional<UserProjection> ofResult = Optional.of(userProjection1);
        when(userDao.save((UserProjection) any())).thenReturn(userProjection);
        when(userDao.findByUserName((String) any())).thenReturn(ofResult);
        assertThrows(Exception.class, () -> jpaUserRepository.createUser(new User("janedoe", "iloveyou", true)));
        verify(userDao).findByUserName((String) any());
    }

    /**
     * Method under test: {@link JpaUserRepository#createUser(User)}
     */
    @Test
    void testCreateUser3() throws Exception {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        when(userDao.save((UserProjection) any())).thenReturn(userProjection);
        when(userDao.findByUserName((String) any())).thenReturn(Optional.empty());
        jpaUserRepository.createUser(new User("janedoe", "iloveyou", true));
        verify(userDao).save((UserProjection) any());
        verify(userDao).findByUserName((String) any());
    }


}

