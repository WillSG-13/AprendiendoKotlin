package com.wilmar.firstapp.FirsApp.SuperHeroApp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //coorrutinas permiten syncrina
    @GET("search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName : String): Response<SuperHeroDataResponse>

    @GET("2386528841539797/{id}")
    suspend fun getSuperHeroID(@Path("id") id:String) :Response<SuperHeroData>

}