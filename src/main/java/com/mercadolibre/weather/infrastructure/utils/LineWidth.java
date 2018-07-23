package com.mercadolibre.weather.infrastructure.utils;

public class LineWidth {

	private final Integer value;

	public LineWidth(PlanePosition position1, PlanePosition position2) {
		Integer x1 = position1.getX();
		Integer y1 = position1.getY();
		Integer x2 = position2.getX();
		Integer y2 = position2.getY();
		Double sqrt = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		this.value = sqrt.intValue();
	}

	public LineWidth(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		LineWidth lineWidth = (LineWidth) o;

		return value != null ? value.equals(lineWidth.value) : lineWidth.value == null;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "LineWidth{value=" + value + '}';
	}
}
