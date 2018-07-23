package com.mercadolibre.weather.actions;

import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.domain.repository.GalaxyRepository;
import com.mercadolibre.weather.domain.service.WeatherService;
import com.mercadolibre.weather.domain.Weather;
import com.mercadolibre.weather.domain.Days;

public class GetDayWeather {

	private GalaxyRepository galaxyRepository;
	private WeatherService weatherService;

	public GetDayWeather(GalaxyRepository galaxyRepository, WeatherService weatherService) {
		this.galaxyRepository = galaxyRepository;
		this.weatherService = weatherService;
	}

	public Weather execute(Days days) {
		Galaxy galaxy = galaxyRepository.getGalaxy();
		return weatherService.predictWeather(galaxy, days);
	}
}
