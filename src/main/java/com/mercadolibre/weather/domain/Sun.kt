package com.mercadolibre.weather.domain

import com.mercadolibre.weather.infrastructure.utils.PlanePosition

class Sun {

	private val planePosition: PlanePosition = PlanePosition(0, 0)

	fun getPlanePosition(): PlanePosition {
		return this.planePosition
	}

}
