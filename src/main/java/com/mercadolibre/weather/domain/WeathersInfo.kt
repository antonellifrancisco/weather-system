package com.mercadolibre.weather.domain

data class WeathersInfo(
		val weathers: List<WeatherPeriods>,
		val peekRainDay: Int
)
