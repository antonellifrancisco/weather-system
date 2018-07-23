package com.mercadolibre.weather.infraestructure.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import com.mercadolibre.weather.domain.Distance;

public class PlanePositionTest {

	@Test
	public void PositionAtDegrees0AndDistance() {
		DegreesPosition degrees = GivenDegressPosition(0);
		Distance distance = GivenDistance(500);

		PlanePosition planePosition = WhenGetPosition(degrees, distance);

		ThenPlanePositionIs(new PlanePosition(500, 0), planePosition);
	}

	@Test
	public void PositionAtDegrees90AndDistance() {
		DegreesPosition degrees = GivenDegressPosition(90);
		Distance distance = GivenDistance(500);

		PlanePosition planePosition = WhenGetPosition(degrees, distance);

		ThenPlanePositionIs(new PlanePosition(0, 500), planePosition);
	}

	@Test
	public void PositionAtDegrees180AndDistance() {
		DegreesPosition degrees = GivenDegressPosition(180);
		Distance distance = GivenDistance(500);

		PlanePosition planePosition = WhenGetPosition(degrees, distance);

		ThenPlanePositionIs(new PlanePosition(-500, 0), planePosition);
	}

	@Test
	public void PositionAtDegrees270AndDistance() {
		DegreesPosition degrees = GivenDegressPosition(270);
		Distance distance = GivenDistance(500);

		PlanePosition planePosition = WhenGetPosition(degrees, distance);

		ThenPlanePositionIs(new PlanePosition(0, -500), planePosition);
	}

	private DegreesPosition GivenDegressPosition(int value) {
		DegreesPosition degreesPosition = mock(DegreesPosition.class);
		when(degreesPosition.getValue()).thenReturn(value);
		return degreesPosition;
	}

	private Distance GivenDistance(int value) {
		return new Distance(value);
	}

	private PlanePosition WhenGetPosition(DegreesPosition degrees, Distance distance) {
		return new PlanePosition(degrees, distance);
	}

	private void ThenPlanePositionIs(PlanePosition expected, PlanePosition actual) {
		Assert.assertEquals(expected, actual);
	}

}
