package com.mercadolibre.weather.infraestructure.rest.representation

import com.mercadolibre.weather.domain.Weather

data class GetWeatherResponse(
		val weather: Weather,
		val day: Integer
)