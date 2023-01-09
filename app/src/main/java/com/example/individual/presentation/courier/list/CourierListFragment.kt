package com.example.individual.presentation.courier.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentCouriersListBinding
import com.example.individual.model.Courier
import com.example.individual.presentation.BaseFragment

class CourierListFragment : BaseFragment() {
    private lateinit var binding: FragmentCouriersListBinding
    private lateinit var viewModel: CourierListViewModel

    private val adapter by lazy {
        CouriersAdapter(
            onFullInfoClick = { courierShort ->
                navigator?.navigateToCourierCreateEdit(
                    courierShort.departmentId,
                    courierShort.id
                )
            },
            onFirstNameClick = {
                sortByName()
                showMessageByToast("Сортировка по названию")
            },
            onDeliveryMethodClick = {
                sortByDeliveryMethod()
                showMessageByToast("Сортировка по профилю")
            },
            onOrdersClick = { courier ->
                navigator?.navigateToOrders(courier)
            }
        )
    }

    private fun sortByName() {
        adapter.items = adapter.items.sortedBy { it.firstName }
    }

    private fun sortByDeliveryMethod() {
        adapter.items = adapter.items.sortedBy { it.deliveryMethod }
    }

    private val initParams: CourierListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_couriers_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCouriers.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.departmentName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToCourierCreateEdit(initParams.departmentId)
        }

        viewModel = ViewModelProvider(this).get(CourierListViewModel::class.java)
        viewModel.getCouriers(initParams.departmentId)
        viewModel.couriersLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(courierFulls: List<Courier>) {
        adapter.items = courierFulls.sortedBy { it.firstName }
    }

    companion object {
        fun newInstance(courierListFragmentInitParams: CourierListFragmentInitParams): CourierListFragment =
            CourierListFragment().provideInitParams(courierListFragmentInitParams) as CourierListFragment
    }
}