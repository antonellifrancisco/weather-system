package com.mercadolibre.weather.userinterface;

import java.awt.*;

import javax.swing.*;

import com.mercadolibre.weather.domain.AlignmentThreshold;
import com.mercadolibre.weather.domain.Galaxy;
import com.mercadolibre.weather.infrastructure.repository.InMemoryGalaxyRepository;
import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.service.AlignmentService;
import com.mercadolibre.weather.domain.service.WeatherService;
import com.mercadolibre.weather.domain.service.LocationService;
import com.mercadolibre.weather.infrastructure.utils.PlanePosition;
import com.mercadolibre.weather.domain.Weather;

public class TestApp extends JPanel {

	private AlignmentService alignmentService = new AlignmentService(new AlignmentThreshold(40));
	private LocationService locationService = new LocationService();
	private WeatherService weatherService = new WeatherService(alignmentService, locationService);
	private Galaxy galaxy = new InMemoryGalaxyRepository().getGalaxy();
	private Days days = new Days(0);
	private Weather weather;
	private int dayNumber = 0;

	public TestApp() {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					days = new Days(dayNumber++);
					weather = weatherService.predictWeather(galaxy, days);
					repaint();
					try {
						Thread.sleep(200);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		thread.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setFontSize(g);
		drawAxis(g);
		drawPlanets(g);
		drawDays(g);
		drawStatus(g);
	}

	private void drawStatus(Graphics g) {
		g.drawString(weather.toString(), coordinate(0), 120);
	}

	private void drawDays(Graphics g) {
		g.drawString("Day " + dayNumber, coordinate(0), 100);
	}

	private void drawPlanets(Graphics g) {
		galaxy.getPlanets().getList().stream().forEach(planet -> {
			drawPlanet(g, planet.getPlanePositionAt(days));
			drawOrbit(g, planet.getDistance().getValue());
		});
		drawPlanet(g, galaxy.getSun().getPlanePosition());
	}

	private void drawPlanet(Graphics g, PlanePosition planePosition) {
		g.setColor(Color.blue);
		Integer size = 200;
		int x = coordinate(planePosition.getX() - (size / 4));
		int y = coordinate(planePosition.getY() - (size / 4));
		g.fillOval(x, y, scale(size), scale(size));
	}

	private void drawOrbit(Graphics g, Integer radius) {
		g.setColor(Color.red);
		g.drawOval(coordinate(-radius), coordinate(-radius), scale(radius * 4), scale(radius * 4));
	}

	private void drawAxis(Graphics g) {
		g.setColor(Color.black);
		g.drawLine(coordinate(-2000), coordinate(0), coordinate(2000), coordinate(0));
		g.drawLine(coordinate(0), coordinate(-2000), coordinate(0), coordinate(2000));
	}

	private int coordinate(int value) {
		int scale = scale(value);
		int center = scale + 500;
		return scale + center;
	}

	private int scale(int value) {
		return value / 12;
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Planets ML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setBackground(Color.WHITE);
		frame.setContentPane(new TestApp());
		frame.setVisible(true);
	}

	private void setFontSize(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	}
}
