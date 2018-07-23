package com.mercadolibre.weather.domain

data class WeathersInfo(
		val weathersPeriods: List<WeatherPeriods>,
		val peekRainDay: Int
)
