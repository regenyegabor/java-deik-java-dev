package com.epam.training.ticketservice.dataaccess.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class UserProjectionTest {
    /**
     * Method under test: {@link UserProjection#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new UserProjection()).canEqual("Other"));
    }

    /**
     * Method under test: {@link UserProjection#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        UserProjection userProjection = new UserProjection();

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(UUID.randomUUID());
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertTrue(userProjection.canEqual(userProjection1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#UserProjection()}
     *   <li>{@link UserProjection#setPrivileged(boolean)}
     *   <li>{@link UserProjection#setUserId(UUID)}
     *   <li>{@link UserProjection#setUserName(String)}
     *   <li>{@link UserProjection#setUserPassword(String)}
     *   <li>{@link UserProjection#toString()}
     *   <li>{@link UserProjection#getUserId()}
     *   <li>{@link UserProjection#getUserName()}
     *   <li>{@link UserProjection#getUserPassword()}
     *   <li>{@link UserProjection#isPrivileged()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserProjection actualUserProjection = new UserProjection();
        actualUserProjection.setPrivileged(true);
        UUID randomUUIDResult = UUID.randomUUID();
        actualUserProjection.setUserId(randomUUIDResult);
        actualUserProjection.setUserName("janedoe");
        actualUserProjection.setUserPassword("iloveyou");
        actualUserProjection.toString();
        assertSame(randomUUIDResult, actualUserProjection.getUserId());
        assertEquals("janedoe", actualUserProjection.getUserName());
        assertEquals("iloveyou", actualUserProjection.getUserPassword());
        assertTrue(actualUserProjection.isPrivileged());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#UserProjection(UUID, String, String, boolean)}
     *   <li>{@link UserProjection#setPrivileged(boolean)}
     *   <li>{@link UserProjection#setUserId(UUID)}
     *   <li>{@link UserProjection#setUserName(String)}
     *   <li>{@link UserProjection#setUserPassword(String)}
     *   <li>{@link UserProjection#toString()}
     *   <li>{@link UserProjection#getUserId()}
     *   <li>{@link UserProjection#getUserName()}
     *   <li>{@link UserProjection#getUserPassword()}
     *   <li>{@link UserProjection#isPrivileged()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        UserProjection actualUserProjection = new UserProjection(UUID.randomUUID(), "janedoe", "iloveyou", true);
        actualUserProjection.setPrivileged(true);
        UUID randomUUIDResult = UUID.randomUUID();
        actualUserProjection.setUserId(randomUUIDResult);
        actualUserProjection.setUserName("janedoe");
        actualUserProjection.setUserPassword("iloveyou");
        actualUserProjection.toString();
        assertSame(randomUUIDResult, actualUserProjection.getUserId());
        assertEquals("janedoe", actualUserProjection.getUserName());
        assertEquals("iloveyou", actualUserProjection.getUserPassword());
        assertTrue(actualUserProjection.isPrivileged());
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        assertNotEquals(userProjection, null);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals2() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        assertNotEquals(userProjection, "Different type to UserProjection");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#equals(Object)}
     *   <li>{@link UserProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");
        assertEquals(userProjection, userProjection);
        int expectedHashCodeResult = userProjection.hashCode();
        assertEquals(expectedHashCodeResult, userProjection.hashCode());
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals4() {
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
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals5() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(false);
        userProjection.setUserId(UUID.randomUUID());
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(UUID.randomUUID());
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals6() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(UUID.randomUUID());
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#equals(Object)}
     *   <li>{@link UserProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals7() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertEquals(userProjection, userProjection1);
        int expectedHashCodeResult = userProjection.hashCode();
        assertEquals(expectedHashCodeResult, userProjection1.hashCode());
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals8() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("iloveyou");
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals9() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName(null);
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals10() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword("janedoe");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Method under test: {@link UserProjection#equals(Object)}
     */
    @Test
    void testEquals11() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword(null);

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword("iloveyou");
        assertNotEquals(userProjection, userProjection1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#equals(Object)}
     *   <li>{@link UserProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals12() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName(null);
        userProjection.setUserPassword("iloveyou");

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName(null);
        userProjection1.setUserPassword("iloveyou");
        assertEquals(userProjection, userProjection1);
        int expectedHashCodeResult = userProjection.hashCode();
        assertEquals(expectedHashCodeResult, userProjection1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserProjection#equals(Object)}
     *   <li>{@link UserProjection#hashCode()}
     * </ul>
     */
    @Test
    void testEquals13() {
        UserProjection userProjection = new UserProjection();
        userProjection.setPrivileged(true);
        userProjection.setUserId(null);
        userProjection.setUserName("janedoe");
        userProjection.setUserPassword(null);

        UserProjection userProjection1 = new UserProjection();
        userProjection1.setPrivileged(true);
        userProjection1.setUserId(null);
        userProjection1.setUserName("janedoe");
        userProjection1.setUserPassword(null);
        assertEquals(userProjection, userProjection1);
        int expectedHashCodeResult = userProjection.hashCode();
        assertEquals(expectedHashCodeResult, userProjection1.hashCode());
    }
}

