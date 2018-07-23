package com.mercadolibre.weather.infraestructure;

import com.mercadolibre.weather.actions.GetDayWeather;
import com.mercadolibre.weather.actions.GetPeriodWeathers;
import com.mercadolibre.weather.domain.AlignmentThreshold;
import com.mercadolibre.weather.domain.repository.GalaxyRepository;
import com.mercadolibre.weather.domain.service.AlignmentService;
import com.mercadolibre.weather.domain.service.LocationService;
import com.mercadolibre.weather.domain.service.RainIntensityService;
import com.mercadolibre.weather.domain.service.WeatherService;
import com.mercadolibre.weather.infraestructure.repository.InMemoryGalaxyRepository;
import com.mercadolibre.weather.infraestructure.rest.GetDayWeatherResource;
import com.mercadolibre.weather.infraestructure.rest.GetPeriodWeathersResource;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration> {

	private final WeatherService weatherService;
	private final LocationService locationService;
	private final GalaxyRepository galaxyRepository;
	private final AlignmentService alignmentService;
	private final RainIntensityService rainIntensityService;

	public Application() {
		super();
		locationService = new LocationService();
		galaxyRepository = new InMemoryGalaxyRepository();
		alignmentService = new AlignmentService(new AlignmentThreshold(40));
		weatherService = new WeatherService(alignmentService, locationService);
		rainIntensityService = new RainIntensityService(weatherService);
	}

	@Override
	public void run(Configuration configuration, Environment environment) {
		GetDayWeather getDayWeather = new GetDayWeather(galaxyRepository, weatherService);
		GetPeriodWeathers getPeriodWeathers = new GetPeriodWeathers(galaxyRepository, weatherService, rainIntensityService);

		GetDayWeatherResource getDayWeatherResource = new GetDayWeatherResource(getDayWeather);
		GetPeriodWeathersResource getPeriodWeathersResource = new GetPeriodWeathersResource(getPeriodWeathers);

		registerResource(environment, getDayWeatherResource);
		registerResource(environment, getPeriodWeathersResource);
	}

	private void registerResource(Environment environment, Object resource) {
		environment.jersey().register(resource);
	}

	public static void main(String[] args) throws Exception {
		new Application().run("server");
	}
}
