package com.firstprojects.jetpackcomposecryptocurrencyapp.service

import com.firstprojects.jetpackcomposecryptocurrencyapp.model.CryptoCurrencyModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface serviceAPI {

    //base_url = https://raw.githubusercontent.com/atilsamancioglu/
    //github.com/huseyin-uslu
    @GET("K21-JSONDataSet/master/crypto.json")
    fun getData() : Observable<CryptoCurrencyModel>
}