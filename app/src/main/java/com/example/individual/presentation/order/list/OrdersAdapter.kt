package com.example.individual.presentation.order.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Order

class OrdersAdapter(
    private val onOrderClick: (Order) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var items: List<Order> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_order, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        private val tvIsfulFilled = itemView.findViewById<TextView>(R.id.tvIsfulFilled)

        fun bind(order: Order) {
            tvAddress.text = order.address

            if (order.isfulFilled) {
                tvIsfulFilled.setText(R.string.ful_filled)
            } else {
                tvIsfulFilled.setText(R.string.unful_filled)
            }

            itemView.setOnClickListener { onOrderClick(order) }
        }
    }
}