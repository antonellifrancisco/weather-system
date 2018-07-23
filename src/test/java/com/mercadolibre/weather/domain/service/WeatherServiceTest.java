package com.mercadolibre.weather.domain.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.Alignment;
import com.mercadolibre.weather.domain.SunLocation;
import com.mercadolibre.weather.domain.Planets;
import com.mercadolibre.weather.domain.Sun;
import com.mercadolibre.weather.domain.Weather;

public class WeatherServiceTest {

	private AlignmentService alignmentService;
	private WeatherService weatherService;
	private LocationService locationService;
	private Galaxy galaxy = new Galaxy(new Sun(), new Planets(new ArrayList<>()));
	private Days days = new Days(0);

	@Before
	public void Before() {
		alignmentService = mock(AlignmentService.class);
		locationService = mock(LocationService.class);
		weatherService = new WeatherService(alignmentService, locationService);
	}

	@Test
	public void planetsAndSunAligned() {
		GivenPlanetsAndSunAligned();
		Weather weather = WhenGetWeather();
		ThenWeatherIs(Weather.DROUGHT, weather);
	}

	@Test
	public void onlyPlanetsAligned() {
		GivenOnlyPlanetsAligned();
		Weather weather = WhenGetWeather();
		ThenWeatherIs(Weather.OPTIMUM, weather);
	}

	@Test
	public void sunInsidePlanets() {
		GivenSunInsidePlanets();
		Weather weather = WhenGetWeather();
		ThenWeatherIs(Weather.RAIN, weather);

	}

	@Test
	public void sunOutsidePlanets() {
		GivenSunOutsidePlanets();
		Weather weather = WhenGetWeather();
		ThenWeatherIs(Weather.NORMAL, weather);
	}

	private void GivenPlanetsAndSunAligned() {
		when(alignmentService.getAlignment(any()))
				.thenReturn(Alignment.ALIGNED)
				.thenReturn(Alignment.ALIGNED);
	}

	private void GivenOnlyPlanetsAligned() {
		when(alignmentService.getAlignment(any()))
				.thenReturn(Alignment.UNALIGNED)
				.thenReturn(Alignment.ALIGNED);
	}

	private void GivenSunInsidePlanets() {
		when(alignmentService.getAlignment(any()))
				.thenReturn(Alignment.UNALIGNED)
				.thenReturn(Alignment.UNALIGNED);

		when(locationService.getLocation(any(), any()))
				.thenReturn(SunLocation.INSIDE);
	}

	private void GivenSunOutsidePlanets() {
		when(alignmentService.getAlignment(any()))
				.thenReturn(Alignment.UNALIGNED)
				.thenReturn(Alignment.UNALIGNED);

		when(locationService.getLocation(any(), any()))
				.thenReturn(SunLocation.OUTSIDE);
	}

	private Weather WhenGetWeather() {
		return weatherService.predictWeather(galaxy, days);
	}

	private void ThenWeatherIs(Weather expected, Weather actual) {
		Assert.assertEquals(expected, actual);
	}
}
