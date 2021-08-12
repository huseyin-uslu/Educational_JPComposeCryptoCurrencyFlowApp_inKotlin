package com.firstprojects.jetpackcomposecryptocurrencyapp.service

import com.firstprojects.jetpackcomposecryptocurrencyapp.model.CryptoCurrencyModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val BASE_URL = "https://raw.githubusercontent.com/atilsamancioglu/"
    private val retrofit: serviceAPI? = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(serviceAPI::class.java)

    fun getRequest() : Observable<CryptoCurrencyModel>? {
        return retrofit?.getData()
    }
//github.com/huseyin-uslu
}