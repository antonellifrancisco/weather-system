package com.mercadolibre.weather.domain.service;

import java.util.List;

import com.mercadolibre.weather.domain.Galaxy;

import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.Weather;

import com.mercadolibre.weather.infraestructure.utils.PlanePosition;
import com.mercadolibre.weather.domain.Alignment;
import com.mercadolibre.weather.domain.SunLocation;

public class WeatherService {

	private final AlignmentService alignmentService;
	private final LocationService locationService;

	public WeatherService(AlignmentService alignmentService, LocationService locationService) {

		this.alignmentService = alignmentService;
		this.locationService = locationService;
	}

	public Weather predictWeather(Galaxy galaxy, Days days) {
		if (planetsAndSunAligned(galaxy, days)) {
			return Weather.DROUGHT;
		}
		if (planetsAligned(galaxy, days)) {
			return Weather.OPTIMUM;
		}
		if (sunInsidePlanets(galaxy, days)) {
			return Weather.RAIN;
		}
		return Weather.NORMAL;
	}

	private Boolean sunInsidePlanets(Galaxy galaxy, Days days) {
		List<PlanePosition> positions = galaxy.getPositions(days);
		SunLocation location = locationService.getLocation(positions, galaxy.getSun().getPlanePosition());
		return location.equals(SunLocation.INSIDE);
	}

	private Boolean planetsAligned(Galaxy galaxy, Days days) {
		List<PlanePosition> positions = galaxy.getPositions(days);
		Alignment alignment = alignmentService.getAlignment(positions);
		return alignment.equals(Alignment.ALIGNED);
	}

	private Boolean planetsAndSunAligned(Galaxy galaxy, Days days) {
		List<PlanePosition> positionsWithSun = galaxy.getPositionsWithSun(days);
		Alignment alignment = alignmentService.getAlignment(positionsWithSun);
		return alignment.equals(Alignment.ALIGNED);
	}

}

