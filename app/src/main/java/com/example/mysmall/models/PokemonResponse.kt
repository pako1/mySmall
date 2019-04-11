package com.example.mysmall.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(@SerializedName("count")val count: Int,
                           @SerializedName("next") var next: String,
                           @SerializedName("results") var results:List<Pokemon>)