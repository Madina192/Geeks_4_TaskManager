package com.example.geeks_4_taskmanager.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geeks_4_taskmanager.data.local.Pref
import com.example.geeks_4_taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding

    private val pref : Pref by lazy {
        Pref(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.setText(pref.getName())
        binding.btnSaveUserName.setOnClickListener{
            pref.saveUserName(binding.etName.text.toString())
        }

    }

}