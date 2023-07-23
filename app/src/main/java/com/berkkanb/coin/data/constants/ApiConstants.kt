package com.berkkanb.coin.data.constants

class ApiConstants private constructor() {

    companion object {

        private const val API_PREFIX = "/api"

        private const val API_VERSION = "/v3"

        private const val BASE_URL = "https://api.coingecko.com"

        const val API_URL = "$BASE_URL$API_PREFIX$API_VERSION/"

    }

}