package com.mercadolibre.weather.infraestructure.utils;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javafx.util.Pair;

import com.mercadolibre.weather.domain.Distance;

public class Line {

	private final PlanePosition position1;
	private final PlanePosition position2;
	private LineWidth width;

	public Line(PlanePosition position1, PlanePosition position2) {
		this.position1 = position1;
		this.position2 = position2;
		this.width = new LineWidth(position1, position2);
	}

	public Distance getDistance(PlanePosition position) {
		Point2D point1 = new Point2D.Double(position1.getX(), position1.getY());
		Point2D point2 = new Point2D.Double(position2.getX(), position2.getY());
		Point2D point3 = new Point2D.Double(position.getX(), position.getY());
		Line2D line2D = new Line2D.Double(point1, point2);
		return new Distance((int) line2D.ptSegDist(point3));
	}

	public LineWidth getWidth() {
		return this.width;
	}

}
