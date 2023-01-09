package com.example.individual.presentation.department.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Department

class DepartmentsAdapter(
    private val onDepartmentClick: (Department) -> Unit,
    private val onDepartmentLongClick: (Department) -> Unit
) : RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    var items: List<Department> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_department, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(department: Department) {
            tvName.text = department.name
            itemView.setOnClickListener {
                onDepartmentClick(department)
            }
            itemView.setOnLongClickListener {
                onDepartmentLongClick(department)
                true
            }
        }
    }
}