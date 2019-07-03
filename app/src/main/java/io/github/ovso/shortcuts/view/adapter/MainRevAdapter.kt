package io.github.ovso.shortcuts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.shortcuts.R
import io.github.ovso.shortcuts.databinding.ItemMainBinding
import io.github.ovso.shortcuts.utils.ResourceProvider
import io.github.ovso.shortcuts.view.adapter.MainRevAdapter.MainRevViewHolder

class MainRevAdapter : RecyclerView.Adapter<MainRevViewHolder>() {
  val items = mutableListOf<App>()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    MainRevViewHolder.create(parent)

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: MainRevViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  private fun getItem(position: Int) = items[position]

  class MainRevViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item:App) {
      binding.viewModel = MainItemViewModel(ResourceProvider(itemView.context), item)
    }

    companion object {
      fun create(parent: ViewGroup): MainRevViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMainBinding>(
          layoutInflater,
          R.layout.item_main,
          parent,
          false
        )
        return MainRevViewHolder(binding)
      }
    }
  }
}