package com.mercadolibre.weather.infraestructure.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import com.mercadolibre.weather.domain.Distance;

public class LineTest {

	@Test
	public void distanceOnHorizontalLine() {
		PlanePosition position1 = GivenPosition(0, 3);
		PlanePosition position2 = GivenPosition(10, 3);
		PlanePosition position3 = GivenPosition(5, 4);

		Distance distance = WhenAskForDistance(position1, position2, position3);

		ThenDistanceIs(new Distance(1), distance);
	}

	@Test
	public void distanceOnVerticalLine() {
		PlanePosition position1 = GivenPosition(5, 2);
		PlanePosition position2 = GivenPosition(5, -1);
		PlanePosition position3 = GivenPosition(3, 0);

		Distance distance = WhenAskForDistance(position1, position2, position3);

		ThenDistanceIs(new Distance(2), distance);
	}

	@Test
	public void distanceOnInclinedLine() {
		PlanePosition position1 = GivenPosition(2, 2);
		PlanePosition position2 = GivenPosition(4, 4);
		PlanePosition position3 = GivenPosition(5, 1);

		Distance distance = WhenAskForDistance(position1, position2, position3);

		ThenDistanceIs(new Distance(2), distance);
	}

	private PlanePosition GivenPosition(int x, int y) {
		PlanePosition position = mock(PlanePosition.class);
		when(position.getX()).thenReturn(x);
		when(position.getY()).thenReturn(y);
		return position;
	}

	private Distance WhenAskForDistance(PlanePosition position1, PlanePosition position2, PlanePosition position3) {
		Line line = new Line(position1, position2);
		return line.getDistance(position3);
	}

	private void ThenDistanceIs(Distance expected, Distance actual) {
		Assert.assertEquals(expected, actual);
	}
}
