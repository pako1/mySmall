package com.example.mysmall.api

import com.example.mysmall.models.PokemonResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RequestPokeInterface {

    @GET("pokemon")
    fun getAllPokemon(): Single<PokemonResponse>
    /*
    In Kotlin, an interface can have a companion object but it is not part of the contract that must be implemented by classes that implement the interface.
    It is just an object associated to the interface that has one singleton instance.
    So it is a place you can store things, but doesn't mean anything to the implementation class.
     */
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun makeRetrofitService(): RequestPokeInterface {
            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RequestPokeInterface::class.java)
        }
    }
}