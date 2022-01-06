package com.solidlucho.bitcoinwidget

 import android.annotation.SuppressLint
 import android.content.Context
 import android.net.ConnectivityManager
 import android.net.NetworkInfo
 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.widget.*
 import androidx.lifecycle.Observer
 import androidx.lifecycle.ViewModelProvider
 import androidx.lifecycle.lifecycleScope
 import androidx.lifecycle.observe
 import com.google.android.gms.ads.AdRequest
 import com.google.android.gms.ads.AdSize
 import com.google.android.gms.ads.AdView
 import com.google.android.gms.ads.MobileAds
 import com.solidlucho.bitcoinwidget.Repository.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel
    private lateinit var precio: String
    var bitcoinPrecio: Int = 0

    val lista = arrayOf(
            "USD to BTC",
            "BTC to USD"
    )

    lateinit var mAdView : AdView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)
        val calcular = findViewById<Button>(R.id.calcular)
        val dolares = findViewById<EditText>(R.id.dolares)
        val resultado = findViewById<TextView>(R.id.Result)
    //   val reload = findViewById<Button>(R.id.reload)
        val spinner1 = findViewById<Spinner>(R.id.spinner1)


        val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        val adSize = AdSize.MEDIUM_RECTANGLE

        val adaptador1 = ArrayAdapter<String>(this, R.layout.spinner_item, lista)

        spinner1.adapter = adaptador1

        if (!isConnected) {
            textView.text = "No internet found"
            calcular.setEnabled(false)
        } else {
            val repository = Repository()
            val viewModelFactory = MainViewModel.MainViewModelFactory(repository)

            fun ApiResponse() {
                //Llamo al ViewModel
                viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                lifecycleScope.launchWhenStarted {
                viewModel.myReponse.observe(
                        this@MainActivity,
                        Observer { response ->
                            //Le doy valor a las Variables a partir del Response
                            precio =
                                response.bitcoin["usd"].toString()
                            textView.text = "1 BTC = $ $precio USD"
                            bitcoinPrecio = response.bitcoin["usd"].toString().toInt()

                            calcular.setOnClickListener {

                                    when (spinner1.selectedItem.toString()) {
                                        "USD to BTC" -> resultado.text = " ${dolares.text} USD = " + ("%.7f".format(dolares.text.toString().toFloat() / bitcoinPrecio)) + " BTC "
                                        "BTC to USD" -> resultado.text = " ${dolares.text} BTC = " + ("%.2f".format(dolares.text.toString().toFloat() * bitcoinPrecio)) + " USD "


                                }
                            }})} }

                            ApiResponse()

                        }






        }
    }