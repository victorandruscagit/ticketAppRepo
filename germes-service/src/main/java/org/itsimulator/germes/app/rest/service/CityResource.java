package org.itsimulator.germes.app.rest.service;

import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;


public class CityResource {
    public List<String> findCities(){
        return Lists.newArrayList("Odessa", "Kiev");

    }
}
