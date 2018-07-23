package com.mercadolibre.weather.infrastructure.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class WeathersInfoResponse(
		@get:JsonProperty("weathers") val weathers: List<WeatherPeriodsResponse>,
		@get:JsonProperty("peek_rain_day") val peekRainDay: Int
)