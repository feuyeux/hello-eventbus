package org.feuyeux.eda;

import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloEventsTest {
    private static final Logger LOG = LoggerFactory.getLogger(HelloEventsTest.class);

    private EventListener listener;
    private EventBus eventBus;

    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        listener = new EventListener();
        eventBus.register(listener);
    }

    @AfterEach
    public void tearDown() {
        eventBus.unregister(listener);
    }

    @Test
    public void testFire() {
        eventBus.post("String Event");
        LOG.info("count:{}", listener.getEventsHandled());
        assertEquals(1, listener.getEventsHandled());
        eventBus.post(100);
        LOG.info("count:{}", listener.getEventsHandled());
        assertEquals(1, listener.getEventsHandled());
        CustomEvent customEvent = new CustomEvent("Custom Event");
        eventBus.post(customEvent);
        assertEquals(2, listener.getEventsHandled());
        LOG.info("count:{}", listener.getEventsHandled());
    }
}
