package com.mercadolibre.weather.infraestructure.rest;

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
import com.mercadolibre.weather.infraestructure.rest.representation.GetWeathersResponse;

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
		GetWeathersResponse response = new GetWeathersResponse(weathersInfo);
		return Response.ok(response).build();
	}

}
