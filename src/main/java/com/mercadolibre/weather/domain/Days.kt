package com.mercadolibre.weather.domain

data class Days(val value: Int) {

	init {
		if (value < 0) {
			throw IllegalArgumentException()
		}
	}

}
