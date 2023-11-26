package com.epam.training.ticketservice.presentation.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jline.utils.AttributedString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TicketServicePromptProvider.class})
@ExtendWith(SpringExtension.class)
class TicketServicePromptProviderTest {
    @Autowired
    private TicketServicePromptProvider ticketServicePromptProvider;

    /**
     * Method under test: {@link TicketServicePromptProvider#getPrompt()}
     */
    @Test
    void testGetPrompt() {
        AttributedString actualPrompt = ticketServicePromptProvider.getPrompt();
        assertEquals(Short.SIZE, actualPrompt.length());
        AttributedString toAttributedStringResult = actualPrompt.toAttributedString();
        assertEquals(actualPrompt, toAttributedStringResult);
        assertEquals("Ticket service> ", toAttributedStringResult.toAnsi());
    }
}

