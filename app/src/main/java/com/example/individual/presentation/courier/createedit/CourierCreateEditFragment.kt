package com.example.individual.presentation.courier.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentCourierCreateEditBinding
import com.example.individual.model.Courier
import com.example.individual.presentation.BaseFragment

class CourierCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentCourierCreateEditBinding
    private lateinit var viewModel: CourierCreateEditViewModel
    private val initParams: CourierCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_courier_create_edit,
                container,
                false
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
                viewModel.deleteCourier()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteCourier()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(CourierCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getCourier(it)
        }
        viewModel.courierLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {

            val courier = Courier(
                id = 0,
                departmentId = initParams.departmentId,
                firstName = etFirstName.text.toString(),
                lastName = etLastName.text.toString(),
                middleName = etMiddleName.text.toString(),
                deliveryMethod = etDeliveryMethod.text.toString(),
                unfulfilledOrders = 0,
                allOrders = 0
            )

            val message = courier.validate()
            if (message != null) {
                showMessageByToast(message)
                return
            }

            viewModel.saveCourier(courier)
            closeFragment()
        }
    }

    private fun Courier.validate(): String? {
        return when {
            firstName.isBlank() -> "Заполните имя курьера"
            lastName.isBlank() -> "Заполните фамилию курьера"
            middleName.isBlank() -> "Заполните отчество курьера"
            deliveryMethod.isBlank() -> "Заполните способ доставки"
            else -> null
        }
    }

    private fun updateUI(courier: Courier?) {
        courier?.let {
            with(binding) {
                etFirstName.setText(courier.firstName)
                etLastName.setText(courier.lastName)
                etMiddleName.setText(courier.middleName)
                etDeliveryMethod.setText(courier.middleName)
            }
        }
    }

    companion object {
        fun newInstance(initParams: CourierCreateEditFragmentInitParams) =
            CourierCreateEditFragment().provideInitParams(initParams) as CourierCreateEditFragment
    }
}
