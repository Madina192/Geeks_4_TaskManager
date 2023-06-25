package com.example.geeks_4_taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.geeks_4_taskmanager.App
import com.example.geeks_4_taskmanager.R
import com.example.geeks_4_taskmanager.databinding.FragmentHomeBinding
import com.example.geeks_4_taskmanager.model.Task
import com.example.geeks_4_taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = TaskAdapter(this::onLongClick, this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTasks()
        navigateToTaskFragment()

        binding.recyclerView.adapter = adapter
    }

    private fun navigateToTaskFragment() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun addTasks() {
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
    }

    private fun onLongClick(task: Task) {
        val alertDialogDelete = AlertDialog.Builder(requireContext())
        alertDialogDelete.setMessage(getString(R.string.delete_message))

        alertDialogDelete.setPositiveButton(getString(R.string.yes)) { _, _ ->
            App.db.taskDao().delete(task)
            addTasks()
        }

        alertDialogDelete.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog?.cancel()
        }
        alertDialogDelete.create().show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf("task" to task))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}