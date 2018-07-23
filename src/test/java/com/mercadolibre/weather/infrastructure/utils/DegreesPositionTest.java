package com.mercadolibre.weather.infrastructure.utils;

import org.junit.Assert;
import org.junit.Test;

import com.mercadolibre.weather.domain.Direction;
import com.mercadolibre.weather.domain.Displacement;

public class DegreesPositionTest {

	@Test
	public void PositionAtClockwiseDirection() {
		Displacement displacement = GivenADisplacement(30);
		Direction direction = GivenClockwiseDirection();

		DegreesPosition degreesPosition = WhenGetPosition(displacement, direction);

		ThenPositionIs(new DegreesPosition(30), degreesPosition);
	}

	@Test
	public void PositionAtClockwiseDirectionMoreThanFullTurn() {
		Displacement displacement = GivenADisplacement(380);
		Direction direction = GivenClockwiseDirection();

		DegreesPosition degreesPosition = WhenGetPosition(displacement, direction);

		ThenPositionIs(new DegreesPosition(20), degreesPosition);
	}

	@Test
	public void PositionAtClockwiseAntiDirection() {
		Displacement displacement = GivenADisplacement(10);
		Direction direction = GivenAntiClockwiseDirection();

		DegreesPosition degreesPosition = WhenGetPosition(displacement, direction);

		ThenPositionIs(new DegreesPosition(350), degreesPosition);
	}

	@Test
	public void PositionAtAntiClockwiseDirectionMoreThanFullTurn() {
		Displacement displacement = GivenADisplacement(390);
		Direction direction = GivenAntiClockwiseDirection();

		DegreesPosition degreesPosition = WhenGetPosition(displacement, direction);

		ThenPositionIs(new DegreesPosition(330), degreesPosition);
	}

	private Displacement GivenADisplacement(int value) {
		return new Displacement(value);
	}

	private Direction GivenClockwiseDirection() {
		return new Direction(Direction.Type.CLOCKWISE);
	}

	private Direction GivenAntiClockwiseDirection() {
		return new Direction(Direction.Type.ANTI_CLOCKWISE);
	}

	private DegreesPosition WhenGetPosition(Displacement displacement, Direction direction) {
		return new DegreesPosition(displacement, direction);
	}

	private void ThenPositionIs(DegreesPosition expected, DegreesPosition actual) {
		Assert.assertEquals(expected, actual);
	}

}
