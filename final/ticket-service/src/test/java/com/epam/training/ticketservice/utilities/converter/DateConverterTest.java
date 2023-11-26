package com.epam.training.ticketservice.utilities.converter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DateConverter.class})
@ExtendWith(SpringExtension.class)
class DateConverterTest {
    @Autowired
    private DateConverter dateConverter;

    /**
     * Method under test: {@link DateConverter#convertStringToDate(String)}
     */
    @Test
    void testConvertStringToDate() throws Exception {
        assertThrows(Exception.class, () -> dateConverter.convertStringToDate("2020-03-01"));
    }


    /**
     * Method under test: {@link DateConverter#convertDateToString(java.util.Date)}
     */
    @Test
    void testConvertDateToString3() {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.getTime()).thenReturn(10L);
        dateConverter.convertDateToString(date);
        verify(date).getTime();
    }
}

