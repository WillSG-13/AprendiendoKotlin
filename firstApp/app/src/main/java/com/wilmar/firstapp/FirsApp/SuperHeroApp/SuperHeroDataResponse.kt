package com.wilmar.firstapp.FirsApp.SuperHeroApp

import com.google.gson.annotations.SerializedName

//literalmente la respuesta de la consulta
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItemResponse>
)

//los heroes contenidos en el array de la respuesta de la consulta
data class SuperHeroItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImageResponse
)

data class SuperHeroImageResponse(@SerializedName("url")val url :String)