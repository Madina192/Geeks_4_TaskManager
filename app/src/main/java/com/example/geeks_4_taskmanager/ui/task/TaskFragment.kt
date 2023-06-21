package com.example.geeks_4_taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.geeks_4_taskmanager.App
import com.example.geeks_4_taskmanager.databinding.FragmentTaskBinding
import com.example.geeks_4_taskmanager.model.Task

class TaskFragment : Fragment() {
    private lateinit var binding : FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener{
            onSave()
        }
    }

    private fun onSave() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDescr.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }
}