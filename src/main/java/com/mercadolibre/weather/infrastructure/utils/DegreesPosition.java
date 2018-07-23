package com.mercadolibre.weather.infrastructure.utils;

import com.mercadolibre.weather.domain.Direction;
import com.mercadolibre.weather.domain.Displacement;

public class DegreesPosition {

	private final Integer value;
	private final int MAX_DEGREES = 360;

	public DegreesPosition(Integer value) {
		if (value < 0) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public DegreesPosition(Displacement displacement, Direction direction) {
		int modulusReminder = getModulusReminder(displacement);

		if (direction.getType().equals(Direction.Type.CLOCKWISE)) {
			this.value = modulusReminder;
		} else {
			this.value = MAX_DEGREES - modulusReminder;
		}
	}

	private int getModulusReminder(Displacement displacement) {
		return displacement.getValue() % MAX_DEGREES;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DegreesPosition that = (DegreesPosition) o;

		return value != null ? value.equals(that.value) : that.value == null;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "DegreesPosition{" + "value=" + value + '}';
	}
}
