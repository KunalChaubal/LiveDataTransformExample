package com.transformations.sample.data.remote.model.example


import com.google.gson.annotations.SerializedName

data class BreedDetail(
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String
)