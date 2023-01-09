package com.example.individual.presentation.courier.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Courier
import com.example.individual.utils.DialogUtils

class CouriersAdapter(
    private val onFullInfoClick: (Courier) -> Unit,
    private val onOrdersClick: (Courier) -> Unit,
    private val onFirstNameClick: () -> Unit,
    private val onDeliveryMethodClick: () -> Unit,
) : RecyclerView.Adapter<CouriersAdapter.ViewHolder>() {

    var items: List<Courier> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_courier, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFirstNameTitle = itemView.findViewById<TextView>(R.id.tvFirstNameTitle)
        private val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        private val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        private val tvMiddleName = itemView.findViewById<TextView>(R.id.tvMiddleName)
        private val tvDeliveryMethodTitle = itemView.findViewById<TextView>(R.id.tvDeliveryMethodTitle)
        private val tvDeliveryMethod = itemView.findViewById<TextView>(R.id.tvDeliveryMethod)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)
        private val tvOrders = itemView.findViewById<TextView>(R.id.tvOrders)

        fun bind(courier: Courier) {
            tvFirstName.text = courier.firstName + courier.lastName + courier.middleName
            tvDeliveryMethod.text = courier.deliveryMethod

            tvFirstNameTitle.setOnLongClickListener {
                onFirstNameClick()
                true
            }
            tvDeliveryMethodTitle.setOnLongClickListener {
                onDeliveryMethodClick()
                true
            }

            tvOrders.setOnClickListener { onOrdersClick(courier) }
            tvFullInfo.setOnClickListener { onFullInfoClick(courier) }
            tvAdditionalInfo.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Количество не выполненных заказов: ${courier.unfulfilledOrders}" +
                            "\nВсего заказов: ${courier.allOrders}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}