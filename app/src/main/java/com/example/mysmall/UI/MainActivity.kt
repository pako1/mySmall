package com.example.mysmall.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.mysmall.PokeAdapter
import com.example.mysmall.R
import com.example.mysmall.api.RequestPokeInterface
import com.example.mysmall.models.Pokemon
import com.example.mysmall.models.PokemonResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    private var linearLayoutManager = LinearLayoutManager(this)
    private var pokeAdapter: PokeAdapter = PokeAdapter()

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        loadJson()

    }

    private fun initRecyclerView() {
        rv_poke_list.adapter = pokeAdapter
        rv_poke_list.layoutManager = linearLayoutManager
    }

    private fun loadJson() {
        val retrofit = RequestPokeInterface.makeRetrofitService()

        disposables.add(
            retrofit.getAllPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pokeAdapter.list = it.results
                }, this::handleError)
        )
    }

    private fun handleError(error: Throwable) {
        Timber.e(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }


}
