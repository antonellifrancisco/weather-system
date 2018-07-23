package com.mercadolibre.weather.domain

import com.mercadolibre.weather.infraestructure.utils.DegreesPosition
import com.mercadolibre.weather.infraestructure.utils.PlanePosition

class Planet(private val speed: Speed, val distance: Distance, private val direction: Direction) {

	fun getPlanePositionAt(days: Days): PlanePosition {
		val displacement = speed.getDisplacement(days)
		val degreesPosition = DegreesPosition(displacement, direction)
		return PlanePosition(degreesPosition, distance)
	}

}
