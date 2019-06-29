package io.github.ovso.shortcuts.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.shortcuts.view.adapter.MainRevAdapter.MainRevViewHolder

class MainRevAdapter : RecyclerView.Adapter<MainRevViewHolder>() {
  val items = JsonArray()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRevViewHolder(parent)

  override fun getItemCount() = items.size()

  override fun onBindViewHolder(holder: MainRevViewHolder, position: Int) {

  }

  inner class MainRevViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: JsonElement) {

    }
  }
}