package com.mercadolibre.weather.infraestructure.rest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadolibre.weather.actions.GetDayWeather;
import com.mercadolibre.weather.domain.Weather;
import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.infraestructure.rest.representation.GetWeatherResponse;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class GetDayWeatherResource {

	private final GetDayWeather getWeatherOfDay;

	public GetDayWeatherResource(GetDayWeather getWeatherOfDay) {
		this.getWeatherOfDay = getWeatherOfDay;
	}

	@GET
	public Response getWeather(@QueryParam("day") @NotNull Integer day) {
		Weather weather = getWeatherOfDay.execute(new Days(day));
		GetWeatherResponse response = new GetWeatherResponse(weather, day);
		return Response.ok(response).build();
	}

}
