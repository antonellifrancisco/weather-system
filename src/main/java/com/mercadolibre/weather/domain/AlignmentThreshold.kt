package com.mercadolibre.weather.domain

class AlignmentThreshold(val value: Int) {

	init {
		if (value < 0) {
			throw IllegalArgumentException()
		}
	}

}
