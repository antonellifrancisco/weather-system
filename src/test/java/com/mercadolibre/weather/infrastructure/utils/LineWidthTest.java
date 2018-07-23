package com.mercadolibre.weather.infrastructure.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class LineWidthTest {

	@Test
	public void horizontalLineWidth() {
		PlanePosition position1 = GivenPosition(1, 3);
		PlanePosition position2 = GivenPosition(3, 3);

		LineWidth lineWidth = WhenAskForWidth(position1, position2);
		ThenWidthIs(new LineWidth(2), lineWidth);
	}

	@Test
	public void verticalLineWidth() {
		PlanePosition position1 = GivenPosition(2, 1);
		PlanePosition position2 = GivenPosition(7, 1);

		LineWidth lineWidth = WhenAskForWidth(position1, position2);
		ThenWidthIs(new LineWidth(5), lineWidth);
	}

	@NotNull
	private PlanePosition GivenPosition(int x, int y) {
		PlanePosition position = mock(PlanePosition.class);
		when(position.getX()).thenReturn(x);
		when(position.getY()).thenReturn(y);
		return position;
	}

	private LineWidth WhenAskForWidth(PlanePosition position1, PlanePosition position2) {
		return new LineWidth(position1, position2);
	}

	private void ThenWidthIs(LineWidth expected, LineWidth actual) {
		Assert.assertEquals(expected, actual);
	}
}
