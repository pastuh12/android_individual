package com.example.individual.presentation.department.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentDepartmentsListBinding
import com.example.individual.model.Department
import com.example.individual.presentation.BaseFragment

class DepartmentListFragment : BaseFragment() {
    private lateinit var binding: FragmentDepartmentsListBinding
    private lateinit var viewModel: DepartmentListViewModel

    private val adapter by lazy {
        DepartmentsAdapter(
            onDepartmentClick = { department ->
                navigator?.navigateToCouriers(department)
            },
            onDepartmentLongClick = { department ->
                navigator?.navigateToDepartmentCreateEdit(department.id)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_departments_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDepartments.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToDepartmentCreateEdit() }

        viewModel = ViewModelProvider(this).get(DepartmentListViewModel::class.java)
        viewModel.getDepartments()
        viewModel.departmentsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(departments: List<Department>) {
        adapter.items = departments.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = DepartmentListFragment()
    }
}
