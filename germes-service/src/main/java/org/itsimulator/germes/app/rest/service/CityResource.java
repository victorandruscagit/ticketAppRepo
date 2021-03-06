package org.itsimulator.germes.app.rest.service;

import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("cities")
public class CityResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> findCities(){
        return Lists.newArrayList("Odessa", "Kiev");

    }
}
