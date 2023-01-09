package com.example.individual.presentation.order.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrderCreateEditBinding
import com.example.individual.model.Order
import com.example.individual.presentation.BaseFragment

class OrderCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderCreateEditBinding
    private lateinit var viewModel: OrderCreateEditViewModel
    private val initParams: OrderCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order_create_edit, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteOrder()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(OrderCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getOrder(it)
        }
        viewModel.orderLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val address = etAddress.text.toString()
            val prise = etPrise.text.toString()
            if (address.isBlank()) {
                showMessageByToast("Заполните адресс")
                return
            }
            if(prise.isBlank()){
                showMessageByToast("Заполните сумму заказа")
                return
            }
            if(prise.toIntOrNull() === null){
                showMessageByToast("Сумма заказа должна быть числом")
                return
            }


            val order = Order(
                id = 0,
                courierId = initParams.courierId,
                address = etAddress.text.toString(),
                isfulFilled = cbFulFilled.isChecked,
                prise = etPrise.text.toString().toInt()
            )

            viewModel.saveOrder(order)
            closeFragment()
        }
    }

    private fun updateUI(order: Order?) {
        order?.let {
            with(binding) {
                etAddress.setText(order.address)
                cbFulFilled.isChecked = order.isfulFilled
                etPrise.setText(order.prise.toString())
            }
        }
    }

    companion object {
        fun newInstance(initParams: OrderCreateEditFragmentInitParams) =
            OrderCreateEditFragment().provideInitParams(initParams) as OrderCreateEditFragment
    }
}
