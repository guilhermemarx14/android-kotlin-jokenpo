package com.example.jokenpo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.jokenpo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var goToPlayerAction: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        goToPlayerAction = HomeFragmentDirections.actionHomeFragmentToPlayerFragment()


        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.playButton.setOnClickListener {
            it.findNavController().navigate(goToPlayerAction)
         }
    }

}