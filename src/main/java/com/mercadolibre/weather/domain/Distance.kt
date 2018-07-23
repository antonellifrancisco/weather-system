package com.mercadolibre.weather.domain

data class Distance(val value: Int) {

	init {
		if (value < 0) {
			throw IllegalArgumentException()
		}
	}

}
