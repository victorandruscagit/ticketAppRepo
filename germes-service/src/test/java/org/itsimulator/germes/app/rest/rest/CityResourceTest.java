package org.itsimulator.germes.app.rest.rest;

import org.glassfish.jersey.test.JerseyTest;
import org.itsimulator.germes.app.rest.service.config.JerseyConfig;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import java.util.List;

public class CityResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new JerseyConfig();
    }

    @Test
    public void testFindCities() {
        List<?> cities = target("cities").request().get(List.class);
        assertNotNull(cities);
        assertTrue(cities.contains("Odessa"));
        assertTrue(cities.contains("Kiev"));

    }
}
