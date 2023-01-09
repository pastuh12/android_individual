package com.example.individual.presentation.order.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrdersListBinding
import com.example.individual.model.Order
import com.example.individual.presentation.BaseFragment

class OrderListFragment : BaseFragment() {
    private lateinit var binding: FragmentOrdersListBinding
    private lateinit var viewModel: OrderListViewModel

    private val adapter by lazy {
        OrdersAdapter(
            onOrderClick = { order ->
                navigator?.navigateToOrderCreateEdit(
                    order.courierId,
                    order.id
                )
            }
        )
    }

    private val initParams: OrderListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_orders_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvOrders.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.courierName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToOrderCreateEdit(initParams.courierId)
        }

        viewModel = ViewModelProvider(this).get(OrderListViewModel::class.java)
        viewModel.getOrders(initParams.courierId)
        viewModel.ordersLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(orderFulls: List<Order>) {
        adapter.items = orderFulls.sortedByDescending { it.address }
    }

    companion object {
        fun newInstance(orderListFragmentInitParams: OrderListFragmentInitParams): OrderListFragment =
            OrderListFragment().provideInitParams(orderListFragmentInitParams) as OrderListFragment
    }
}