package com.mercadolibre.weather.infrastructure.repository;

import java.util.List;

import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.domain.Planet;
import com.mercadolibre.weather.domain.Planets;
import com.mercadolibre.weather.domain.Sun;
import com.mercadolibre.weather.domain.repository.GalaxyRepository;
import com.mercadolibre.weather.infrastructure.utils.StarFactory;

public class InMemoryGalaxyRepository implements GalaxyRepository {

	private final Galaxy galaxy;

	public InMemoryGalaxyRepository() {
		Planet ferengi = StarFactory.getFerengi();
		Planet betasoide = StarFactory.getBetasoide();
		Planet vulcano = StarFactory.getVulcano();
		Sun sun = StarFactory.getSun();
		galaxy = new Galaxy(sun, new Planets(List.of(ferengi, betasoide, vulcano)));
	}

	public Galaxy getGalaxy() {
		return galaxy;
	}

}
