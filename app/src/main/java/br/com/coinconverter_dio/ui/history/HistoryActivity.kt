package br.com.coinconverter_dio.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.coinconverter_dio.core.extensions.createDialog
import br.com.coinconverter_dio.core.extensions.createProgressDialog
import br.com.coinconverter_dio.databinding.ActivityHistoryBinding
import br.com.coinconverter_dio.presentation.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {

    private val adapter by lazy { HistoryListAdapter() }
    private val viewModel by viewModel<HistoryViewModel>()
    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initViewModel()
        bindObserve()

    }

    private fun init(){
        binding.run {
            with(rvHistory){
                adapter = this@HistoryActivity.adapter
                addItemDecoration(DividerItemDecoration(this@HistoryActivity, DividerItemDecoration.HORIZONTAL))
            }
        }
    }
    private fun bindObserve() {
        viewModel.state.observe(this) {
            when (it) {
                HistoryViewModel.State.Loading -> dialog.show()
                is HistoryViewModel.State.Error -> {
                    dialog.dismiss()
                    createDialog{
                        setMessage(it.error.message)
                    }.show()
                }
                is HistoryViewModel.State.Sucess -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }
    private fun initViewModel(){
        lifecycle.addObserver(viewModel)
    }
}