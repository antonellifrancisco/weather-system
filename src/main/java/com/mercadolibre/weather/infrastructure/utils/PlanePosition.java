package com.mercadolibre.weather.infrastructure.utils;

import com.mercadolibre.weather.domain.Distance;

public class PlanePosition {

	private final Integer x;
	private final Integer y;

	public PlanePosition(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public PlanePosition(DegreesPosition degreesPosition, Distance distance) {
		this.x = getAdjacentCathet(degreesPosition, distance);
		this.y = getOppositeCathet(degreesPosition, distance);
	}

	private int getAdjacentCathet(DegreesPosition degreesPosition, Distance distance) {
		double radians = getRadians(degreesPosition);
		double cosine = Math.cos(radians);
		return getCathet(distance, cosine);
	}

	private int getOppositeCathet(DegreesPosition degreesPosition, Distance distance) {
		double radians = getRadians(degreesPosition);
		double sine = Math.sin(radians);
		return getCathet(distance, sine);
	}

	private double getRadians(DegreesPosition degreesPosition) {
		return Math.toRadians(degreesPosition.getValue());
	}

	private int getCathet(Distance distance, double cos) {
		return (int) (cos * distance.getValue());
	}

	public Integer getX() {
		return this.x;
	}

	public Integer getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PlanePosition that = (PlanePosition) o;

		if (x != null ? !x.equals(that.x) : that.x != null) {
			return false;
		}
		return y != null ? y.equals(that.y) : that.y == null;
	}

	@Override
	public int hashCode() {
		int result = x != null ? x.hashCode() : 0;
		result = 31 * result + (y != null ? y.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PlanePosition{" + "x=" + x + ", y=" + y + '}';
	}
}
