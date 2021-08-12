package com.firstprojects.jetpackcomposecryptocurrencyapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstprojects.jetpackcomposecryptocurrencyapp.model.CryptoCurrencyModel
import com.firstprojects.jetpackcomposecryptocurrencyapp.service.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class DataViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val service = RetrofitService()
    private val TAG = "Get Data Process"
    //github.com/huseyin-uslu
    val mutableLiveData = MutableLiveData<CryptoCurrencyModel>()

     fun getDataFromJson(){
        disposable.add(
            service.getRequest()?.
                subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableObserver<CryptoCurrencyModel>(){
                    override fun onNext(t: CryptoCurrencyModel?) {
                       mutableLiveData.value = t
                        Log.i(TAG, "onNext: SUCCESSFUL")
                    }
                    //github.com/huseyin-uslu
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        Log.e(TAG, "onError: ${e?.localizedMessage}", )
                        mutableLiveData.value = null
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete: it's done succesfully.")
                    }
//github.com/huseyin-uslu
                })


        )
    }
}