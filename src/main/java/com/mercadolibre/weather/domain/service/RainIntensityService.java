package com.mercadolibre.weather.domain.service;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.domain.RainIntensity;
import com.mercadolibre.weather.domain.Weather;
import com.mercadolibre.weather.infraestructure.utils.Combinator;
import com.mercadolibre.weather.infraestructure.utils.Line;
import com.mercadolibre.weather.infraestructure.utils.PlanePosition;

public class RainIntensityService {

	private final WeatherService weatherService;

	public RainIntensityService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public OptionalInt getPeekRainDay(Galaxy galaxy, Days days) {
		List<RainIntensity> intensities = collectIntensities(galaxy, days);
		return getPeekDay(intensities);
	}

	private RainIntensity getIntensity(Galaxy galaxy, Days days) {
		if (isRaining(galaxy, days)) {
			List<PlanePosition> positions = galaxy.getPositions(days);
			Integer value = Combinator.execute(positions).stream()
					.map(tuple -> new Line(tuple.getKey(), tuple.getValue()))
					.mapToInt(line -> line.getWidth().getValue())
					.sum();
			return new RainIntensity(value);
		}
		return new RainIntensity(0);
	}

	private List<RainIntensity> collectIntensities(Galaxy galaxy, Days days) {
		return IntStream.range(0, days.getValue())
				.mapToObj(day -> getIntensity(galaxy, new Days(day)))
				.collect(Collectors.toList());
	}

	private OptionalInt getPeekDay(List<RainIntensity> intensities) {
		return IntStream.range(0, intensities.size())
				.reduce((a, b) -> intensities.get(a).getValue() < intensities.get(b).getValue() ? b : a);
	}

	private boolean isRaining(Galaxy galaxy, Days days) {
		return weatherService.predictWeather(galaxy, days).equals(Weather.RAIN);
	}
}
