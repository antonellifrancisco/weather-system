package com.mercadolibre.weather.domain

data class Displacement(val value: Int) {

	init {
		if (value < 0) {
			throw IllegalArgumentException()
		}
	}

	fun multiply(multiplier: Int): Displacement {
		return Displacement(this.value * multiplier)
	}

}
