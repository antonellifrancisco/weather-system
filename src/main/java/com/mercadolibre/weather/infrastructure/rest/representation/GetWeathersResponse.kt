package com.mercadolibre.weather.infrastructure.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetWeathersResponse(
		@get:JsonProperty("info") val weathersInfo: WeathersInfoResponse
)