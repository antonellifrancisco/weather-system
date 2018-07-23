package com.mercadolibre.weather.infraestructure.utils;

import com.mercadolibre.weather.domain.Direction;
import com.mercadolibre.weather.domain.Displacement;
import com.mercadolibre.weather.domain.Distance;
import com.mercadolibre.weather.domain.Planet;
import com.mercadolibre.weather.domain.Speed;
import com.mercadolibre.weather.domain.Sun;

public class StarFactory {

	public static Sun getSun() {
		return new Sun();
	}

	public static Planet getFerengi() {
		Direction direction = new Direction(Direction.Type.CLOCKWISE);
		Speed speed = new Speed(new Displacement(1));
		Distance distance = new Distance(500);
		return new Planet(speed, distance, direction);
	}

	public static Planet getBetasoide() {
		Direction direction = new Direction(Direction.Type.CLOCKWISE);
		Speed speed = new Speed(new Displacement(3));
		Distance distance = new Distance(2000);
		return new Planet(speed, distance, direction);
	}

	public static Planet getVulcano() {
		Direction direction = new Direction(Direction.Type.ANTI_CLOCKWISE);
		Speed speed = new Speed(new Displacement(5));
		Distance distance = new Distance(1000);
		return new Planet(speed, distance, direction);
	}

}
