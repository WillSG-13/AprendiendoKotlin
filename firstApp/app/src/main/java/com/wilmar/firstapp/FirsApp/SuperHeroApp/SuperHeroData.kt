package com.wilmar.firstapp.FirsApp.SuperHeroApp

import com.google.gson.annotadtions.SerializedName

data class SuperHeroData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerStats: PowerStats,
    @SerializedName("image") val image: Image
)

data class PowerStats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
)

data class Image(
    @SerializedName("url") val url: String,
)