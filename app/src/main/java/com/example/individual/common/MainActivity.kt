package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.Department
import com.example.individual.model.Courier
import com.example.individual.presentation.department.createedit.DepartmentCreateEditFragment
import com.example.individual.presentation.department.createedit.DepartmentCreateEditFragmentInitParams
import com.example.individual.presentation.department.list.DepartmentListFragment
import com.example.individual.presentation.courier.createedit.CourierCreateEditFragment
import com.example.individual.presentation.courier.createedit.CourierCreateEditFragmentInitParams
import com.example.individual.presentation.courier.list.CourierListFragment
import com.example.individual.presentation.courier.list.CourierListFragmentInitParams
import com.example.individual.presentation.order.createedit.OrderCreateEditFragment
import com.example.individual.presentation.order.createedit.OrderCreateEditFragmentInitParams
import com.example.individual.presentation.order.list.OrderListFragment
import com.example.individual.presentation.order.list.OrderListFragmentInitParams

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(DepartmentListFragment.newInstance())

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
        if (supportFragmentManager.fragments.isEmpty()) {
            finish()
        }
    }

    override fun navigateToCouriers(department: Department) {
        addFragment(
            CourierListFragment.newInstance(
                CourierListFragmentInitParams(
                    department.id,
                    department.name
                )
            )
        )
    }

    override fun navigateToCourierCreateEdit(departmentId: Long, courierId: Long?) {
        addFragment(
            CourierCreateEditFragment.newInstance(
                CourierCreateEditFragmentInitParams(
                    departmentId,
                    courierId
                )
            )
        )
    }

    override fun navigateToDepartmentCreateEdit(id: Long?) {
        addFragment(DepartmentCreateEditFragment.newInstance(DepartmentCreateEditFragmentInitParams(id)))
    }

    override fun navigateToOrderCreateEdit(courierId: Long, id: Long?) {
        addFragment(
            OrderCreateEditFragment.newInstance(
                OrderCreateEditFragmentInitParams(
                    courierId, id
                )
            )
        )
    }

    override fun navigateToOrders(courier: Courier) {
        addFragment(
            OrderListFragment.newInstance(
                OrderListFragmentInitParams(
                    courier.id, courier.firstName
                )
            )
        )
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}