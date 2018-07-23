package com.mercadolibre.weather.domain.service;

import java.util.Comparator;
import java.util.List;

import com.mercadolibre.weather.domain.Alignment;
import com.mercadolibre.weather.domain.AlignmentThreshold;
import com.mercadolibre.weather.domain.Distance;
import com.mercadolibre.weather.infraestructure.utils.Combinator;
import com.mercadolibre.weather.infraestructure.utils.Line;

import javafx.util.Pair;

import com.mercadolibre.weather.infraestructure.utils.PlanePosition;

public class AlignmentService {

	private final AlignmentThreshold tolerance;

	public AlignmentService(AlignmentThreshold tolerance) {

		this.tolerance = tolerance;
	}

	public Alignment getAlignment(List<PlanePosition> positions) {
		if (positions.size() < 2) {
			throw new IllegalArgumentException("requires at least 2 positions");
		}
		if (areAligned(this.tolerance, positions)) {
			return Alignment.ALIGNED;
		}
		return Alignment.UNALIGNED;
	}

	private Boolean areAligned(AlignmentThreshold tolerance, List<PlanePosition> positions)
	{
		Line line = getLineAcrossPoints(positions);
		return positions.stream().noneMatch(position -> {
			Distance distanceFromLine = line.getDistance(position);
			return distanceFromLine.getValue() > tolerance.getValue();
		});
	}

	private Line getLineAcrossPoints(List<PlanePosition> positions) {
		List<Pair<PlanePosition, PlanePosition>> combinations = Combinator.execute(positions);
		return combinations.stream()
				.map(tuple -> new Line(tuple.getKey(), tuple.getValue()))
				.max(Comparator.comparingInt(o -> o.getWidth().getValue())).get();
	}
}
