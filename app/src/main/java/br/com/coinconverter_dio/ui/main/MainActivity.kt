package br.com.coinconverter_dio.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import br.com.coinconverter_dio.R
import br.com.coinconverter_dio.core.extensions.*
import br.com.coinconverter_dio.data.model.Coin
import br.com.coinconverter_dio.databinding.ActivityMainBinding
import br.com.coinconverter_dio.presentation.viewmodel.MainViewModel
import br.com.coinconverter_dio.ui.history.HistoryActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initViewModel()

    }

    private fun initViewModel() {

        viewModel.state.observe(this) {
            when (it) {
                is MainViewModel.State.Error -> {
                    dialog.dismiss()
                    createDialog{
                        setMessage(it.error.message)
                    }.show()
                }
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Sucess -> {
                    sucess(it)
                }
                MainViewModel.State.Saved -> {
                    dialog.dismiss()
                    createDialog{
                        setMessage("Item salvo com sucesso")
                    }.show()
                }
            }
        }
    }

    private fun sucess(it: MainViewModel.State.Sucess) {
        dialog.dismiss()
        binding.btnSalvar.isEnabled = true

        val selectedCoin = binding.inputPara.text
        val coin = Coin.getByName(selectedCoin)

        val result = it.exchange.bid * binding.inputValor.text.toDouble()
        binding.resultado.text = result.formatCurrency(coin.locale)
    }

    private fun init(){

        binding.run {

            bindingAdapters()
            bindingListeners()

        }
    }

    private fun bindingAdapters() {

        val list = Coin.values()
        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)

        with(binding.converter) {
            setAdapter(adapter)
            setText(Coin.BRL.name, false)
        }
        with(binding.para) {
            setAdapter(adapter)
            setText(Coin.USD.name, false)
        }
    }

    private fun bindingListeners() {

        with(binding.inputValor){
            editText?.doAfterTextChanged {
                binding.btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
                binding.btnSalvar.isEnabled = false
            }
        }
       with(binding.btnConverter){
           setOnClickListener {
               it.hideSoftKeyboard()
               val search = "${binding.converter.text}-${binding.para.text}"
               viewModel.getExchangeValue(search)

               Log.e("TAG", "bindingListeners: ${binding.inputValor.text}")
           }
       }
        with(binding.btnSalvar){
            setOnClickListener {
                val value = viewModel.state.value
                (value as? MainViewModel.State.Sucess)?.let {
                    val exchange = it.exchange.copy(bid = it.exchange.bid * binding.inputValor.text.toDouble())
                    viewModel.saveExchange(exchange)
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_history){
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }




}