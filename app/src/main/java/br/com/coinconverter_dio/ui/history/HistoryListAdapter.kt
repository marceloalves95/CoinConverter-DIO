package br.com.coinconverter_dio.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.coinconverter_dio.core.extensions.formatCurrency
import br.com.coinconverter_dio.data.model.Coin
import br.com.coinconverter_dio.data.model.ExchangeResponseValue
import br.com.coinconverter_dio.databinding.ItemHistoryBinding

/**
 * @author RubioAlves
 * Created 11/08/2021 at 14:56
 */
class HistoryListAdapter : ListAdapter<ExchangeResponseValue, HistoryListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExchangeResponseValue) {
            binding.nomeMoeda.text = item.name

            val coin = Coin.getByName(item.codein)
            binding.valorMoeda.text = item.bid.formatCurrency(coin.locale)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ExchangeResponseValue>() {
    override fun areItemsTheSame(oldItem: ExchangeResponseValue, newItem: ExchangeResponseValue) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ExchangeResponseValue, newItem: ExchangeResponseValue) = oldItem.id == newItem.id
}