package com.mercadolibre.weather.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mercadolibre.weather.domain.AlignmentThreshold;
import com.mercadolibre.weather.infraestructure.utils.PlanePosition;
import com.mercadolibre.weather.domain.Alignment;

public class AlignmentServiceTest {

	private AlignmentService alignmentService;

	@Before
	public void Before()
	{
		alignmentService = new AlignmentService(new AlignmentThreshold(0));
	}

	@Test
	public void VerticalPointsAligned() {
		PlanePosition position1 = GivenAPosition(100, 400);
		PlanePosition position2 = GivenAPosition(200, 400);
		PlanePosition position3 = GivenAPosition(300, 400);
		List<PlanePosition> positions = List.of(position1, position2, position3);

		Alignment alignment = WhenAskForAlignment(positions);

		ThenAlignmentIs(Alignment.ALIGNED, alignment);
	}

	@Test
	public void HorizontalPointsAligned() {
		PlanePosition position1 = GivenAPosition(500, 300);
		PlanePosition position2 = GivenAPosition(500, 400);
		PlanePosition position3 = GivenAPosition(500, 500);
		List<PlanePosition> positions = List.of(position1, position2, position3);

		Alignment alignment = WhenAskForAlignment(positions);

		ThenAlignmentIs(Alignment.ALIGNED, alignment);
	}

	@Test
	public void InclinedPointsAligned() {
		PlanePosition position1 = GivenAPosition(200, 200);
		PlanePosition position2 = GivenAPosition(400, 300);
		PlanePosition position3 = GivenAPosition(600, 400);
		List<PlanePosition> positions = List.of(position1, position2, position3);

		Alignment alignment = WhenAskForAlignment(positions);

		ThenAlignmentIs(Alignment.ALIGNED, alignment);
	}

	@Test
	public void PointsUnaligned() {
		PlanePosition position1 = GivenAPosition(200, 200);
		PlanePosition position2 = GivenAPosition(400, 300);
		PlanePosition position3 = GivenAPosition(600, 500);
		List<PlanePosition> positions = List.of(position1, position2, position3);

		Alignment alignment = WhenAskForAlignment(positions);

		ThenAlignmentIs(Alignment.UNALIGNED, alignment);
	}

	private PlanePosition GivenAPosition(int x, int y) {
		PlanePosition planePosition = mock(PlanePosition.class);
		when(planePosition.getX()).thenReturn(x);
		when(planePosition.getY()).thenReturn(y);
		return planePosition;
	}

	private Alignment WhenAskForAlignment(List<PlanePosition> positions) {
		return alignmentService.getAlignment(positions);
	}

	private void ThenAlignmentIs(Alignment expected, Alignment actual) {
		Assert.assertEquals(expected, actual);
	}

}
