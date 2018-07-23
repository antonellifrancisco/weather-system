package com.mercadolibre.weather.domain.service;

import java.awt.*;
import java.util.List;

import com.mercadolibre.weather.domain.SunLocation;
import com.mercadolibre.weather.infrastructure.utils.PlanePosition;

public class LocationService {

	public SunLocation getLocation(List<PlanePosition> positions, PlanePosition position) {
		Polygon polygon = new Polygon();
		positions.stream().forEach(p ->
				polygon.addPoint(p.getX(), p.getY())
		);
		Boolean inside = polygon.contains(position.getX(), position.getY());
		return inside ? SunLocation.INSIDE : SunLocation.OUTSIDE;
	}

}
