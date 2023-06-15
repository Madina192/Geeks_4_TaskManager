package com.example.geeks_4_taskmanager.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.geeks_4_taskmanager.R
import com.example.geeks_4_taskmanager.databinding.FragmentOnBoardingBinding
import com.example.geeks_4_taskmanager.model.OnBoarding
import com.example.geeks_4_taskmanager.ui.onboarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3

class OnBoardingFragment : Fragment() {

    private lateinit var binding : FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this :: onClick)
    private lateinit var indicator : CircleIndicator3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        indicator = binding.indicator
        binding.viewPager.adapter = adapter
        indicator.setViewPager(binding.viewPager)
    }

    private fun onClick() {
        findNavController().navigateUp()
    }



}