package com.firstprojects.jetpackcomposecryptocurrencyapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firstprojects.jetpackcomposecryptocurrencyapp.model.CryptoCurrencyModel
import com.firstprojects.jetpackcomposecryptocurrencyapp.model.CryptoCurrencyModelItem
import com.firstprojects.jetpackcomposecryptocurrencyapp.ui.theme.*
import com.firstprojects.jetpackcomposecryptocurrencyapp.viewmodel.DataViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewModel : DataViewModel
    lateinit var dataList : CryptoCurrencyModel
    //github.com/huseyin-uslu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(DataViewModel::class.java)
        dataList = CryptoCurrencyModel()
        viewModel.getDataFromJson()


        setContent {
            JetpackComposeCryptoCurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }

//github.com/huseyin-uslu
}

@Composable
fun MainScreen(){

    val observableData = remember {mutableStateListOf<CryptoCurrencyModelItem>()}
    val viewModel = ViewModelProvider.NewInstanceFactory().create(DataViewModel::class.java)

   viewModel.getDataFromJson()
    viewModel.mutableLiveData.observe(LocalLifecycleOwner.current,object : Observer<CryptoCurrencyModel>{
        override fun onChanged(t: CryptoCurrencyModel?) {
            t?.let {
                observableData.addAll(it)
            }
        }
    })

    Scaffold(topBar = {CustomTopBar()}){
        CryptoList(data = observableData)

    }
}


@Composable
fun CustomTopBar () {
    TopAppBar (contentPadding = PaddingValues(15.dp),backgroundColor = androidx.compose.ui.graphics.Color.Black){
        Text(text = "Crypto Currency App",fontWeight = FontWeight.ExtraBold,color = Purple700)
    }
}

@Composable
fun CryptoList(data: List<CryptoCurrencyModelItem>) {
    val colorList : ArrayList<Color> = arrayListOf()
    colorList.add(OrangeColor)
    colorList.add(YellowColor)
    colorList.add(GreenColor)
    colorList.add(BrownColor)
    colorList.add(PinkColor)
    colorList.add(PurpleColor)
    colorList.add(BlindOrangeColor)
//github.com/huseyin-uslu
    LazyColumn(){
        var i = 0;
        items(data){item ->
            val colorOrder = i % colorList.count()
            CryptoRow(crypto = item,colorList[colorOrder])
            i++
        }
    }
}

@Composable
fun CryptoRow(crypto : CryptoCurrencyModelItem?,color : Color){
    if(crypto != null){

        val cuR = crypto.currency
        val price = crypto.price
        println("IT'S OKAY!! CRYPTO ROW")

        if(cuR != null && price != null){

            Column (modifier = Modifier
                .fillMaxWidth()
                .background(color = color)){
                Text(cuR,style = MaterialTheme.typography.h4,modifier = Modifier.padding(start = 5.dp),fontWeight = FontWeight.Bold)
                Text(price, style = MaterialTheme.typography.h6,modifier = Modifier.padding(start = 30.dp))
            }

        }
    }
//github.com/huseyin-uslu
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeCryptoCurrencyAppTheme {
       CryptoRow(crypto = CryptoCurrencyModelItem("BTC", "32132131"), OrangeColor)
    }
}//github.com/huseyin-uslu