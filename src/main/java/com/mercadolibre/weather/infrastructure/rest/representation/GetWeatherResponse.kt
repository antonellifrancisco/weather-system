package com.mercadolibre.weather.infrastructure.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty
import com.mercadolibre.weather.domain.Weather

data class GetWeatherResponse(
		@get:JsonProperty("weather") val weather: Weather,
		@get:JsonProperty("day") val day: Integer
)