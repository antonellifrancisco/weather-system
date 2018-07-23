package com.mercadolibre.weather.infrastructure.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty
import com.mercadolibre.weather.domain.Weather

data class WeatherPeriodsResponse(
		@get:JsonProperty("weather") val weather: Weather,
		@get:JsonProperty("periods") val periods: Integer
)
