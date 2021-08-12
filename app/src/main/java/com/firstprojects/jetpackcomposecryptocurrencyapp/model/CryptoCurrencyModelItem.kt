package com.firstprojects.jetpackcomposecryptocurrencyapp.model


import com.google.gson.annotations.SerializedName

data class CryptoCurrencyModelItem(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("price")
    val price: String?
)