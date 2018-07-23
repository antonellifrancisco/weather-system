package com.mercadolibre.weather.domain

class Speed(private val displacementPerDay: Displacement) {

	fun getDisplacement(days: Days): Displacement {
		return displacementPerDay.multiply(days.value)
	}

}
