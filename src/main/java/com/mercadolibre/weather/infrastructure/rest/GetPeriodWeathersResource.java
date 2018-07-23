package com.mercadolibre.weather.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadolibre.weather.actions.GetPeriodWeathers;
import com.mercadolibre.weather.domain.Days;
import com.mercadolibre.weather.domain.WeathersInfo;
import com.mercadolibre.weather.infrastructure.rest.representation.GetWeathersResponse;
import com.mercadolibre.weather.infrastructure.rest.representation.WeatherPeriodsResponse;
import com.mercadolibre.weather.infrastructure.rest.representation.WeathersInfoResponse;

@Path("/weathers")
@Produces(MediaType.APPLICATION_JSON)
public class GetPeriodWeathersResource {

	private final GetPeriodWeathers getPeriodWeathers;

	public GetPeriodWeathersResource(GetPeriodWeathers getPeriodWeathers) {
		this.getPeriodWeathers = getPeriodWeathers;
	}

	@GET
	public Response getWeatherPeriods(@QueryParam("days") @NotNull Integer days) {
		WeathersInfo weathersInfo = getPeriodWeathers.execute(new Days(days));
		WeathersInfoResponse weathersInfoResponse = toResponse(weathersInfo);
		GetWeathersResponse response = new GetWeathersResponse(weathersInfoResponse);
		return Response.ok(response).build();
	}

	private WeathersInfoResponse toResponse(WeathersInfo weathersInfo) {
		List<WeatherPeriodsResponse> weatherPeriodsResponse = weathersInfo.getWeathers().stream()
				.map(weathers -> new WeatherPeriodsResponse(weathers.getWeather(), weathers.getPeriods()))
				.collect(Collectors.toList());
		return new WeathersInfoResponse(weatherPeriodsResponse, weathersInfo.getPeekRainDay());
	}

}
