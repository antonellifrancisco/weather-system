package com.mercadolibre.weather.domain

data class RainIntensity(val value: Int) {

	init {
		if (value < 0) {
			throw IllegalArgumentException()
		}
	}

}
