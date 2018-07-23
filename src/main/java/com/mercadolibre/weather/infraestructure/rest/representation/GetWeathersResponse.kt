package com.mercadolibre.weather.infraestructure.rest.representation

import com.mercadolibre.weather.domain.WeathersInfo

data class GetWeathersResponse(
		val weatherPeriods: WeathersInfo
)