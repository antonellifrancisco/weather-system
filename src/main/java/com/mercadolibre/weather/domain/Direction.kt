package com.mercadolibre.weather.domain

data class Direction(val type: Type) {

	enum class Type {
		CLOCKWISE,
		ANTI_CLOCKWISE
	}

}