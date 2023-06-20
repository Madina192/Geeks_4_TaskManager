package com.example.geeks_4_taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

        saveUserName()
        saveUserImage()
    }

    private fun saveUserImage() {
        Glide.with(binding.ivProfileImage).load(pref.getImage()).into(binding.ivProfileImage)
        binding.ivProfileImage.setOnClickListener{
            chooseImageContract.launch("image/*")
        }
    }

    private val chooseImageContract = registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
        if (image != null) {
            pref.saveImage(image.toString())
            Glide.with(requireContext()).load(image).apply(RequestOptions.circleCropTransform()).into(binding.ivProfileImage)
        }
    }

    private fun saveUserName(){
        binding.etName.setText(pref.getName())
        binding.btnSaveUserName.setOnClickListener{
            pref.saveUserName(binding.etName.text.toString())
        }
    }

}