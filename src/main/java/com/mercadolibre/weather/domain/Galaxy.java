package com.mercadolibre.weather.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.weather.infrastructure.utils.PlanePosition;

public class Galaxy {

	private final Sun sun;
	private final Planets planets;

	public Galaxy(Sun sun, Planets planets) {
		this.sun = sun;
		this.planets = planets;
	}

	public List<PlanePosition> getPositions(Days days) {
		return planets.getList()
				.stream()
				.map(planet -> planet.getPlanePositionAt(days))
				.collect(Collectors.toList());
	}

	public List<PlanePosition> getPositionsWithSun(Days days) {
		List<PlanePosition> positions = getPositions(days);
		positions.add(sun.getPlanePosition());
		return positions;
	}

	public Sun getSun() {
		return sun;
	}

	public Planets getPlanets() {
		return planets;
	}
}
