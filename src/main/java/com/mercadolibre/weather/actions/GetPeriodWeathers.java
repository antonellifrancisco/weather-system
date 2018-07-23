package com.mercadolibre.weather.actions;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.domain.WeatherPeriods;
import com.mercadolibre.weather.domain.WeathersInfo;
import com.mercadolibre.weather.domain.repository.GalaxyRepository;
import com.mercadolibre.weather.domain.service.RainIntensityService;
import com.mercadolibre.weather.domain.service.WeatherService;

public class GetPeriodWeathers {

	private final GalaxyRepository galaxyRepository;
	private final WeatherService weatherService;
	private final RainIntensityService rainIntensityService;

	public GetPeriodWeathers(GalaxyRepository galaxyRepository, WeatherService weatherService, RainIntensityService rainIntensityService) {
		this.galaxyRepository = galaxyRepository;
		this.weatherService = weatherService;
		this.rainIntensityService = rainIntensityService;
	}

	public WeathersInfo execute(Days days) {
		Galaxy galaxy = galaxyRepository.getGalaxy();
		List<WeatherPeriods> weatherPeriods = IntStream.range(0, days.getValue())
				.mapToObj(day -> weatherService.predictWeather(galaxy, new Days(day)))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.map(entry -> new WeatherPeriods(entry.getKey(), entry.getValue().intValue()))
				.collect(Collectors.toList());
		OptionalInt peekRainDay = rainIntensityService.getPeekRainDay(galaxy, days);
		Integer peekDay = peekRainDay.isPresent() ? peekRainDay.getAsInt() : null;
		return new WeathersInfo(weatherPeriods, peekDay);
	}

}
