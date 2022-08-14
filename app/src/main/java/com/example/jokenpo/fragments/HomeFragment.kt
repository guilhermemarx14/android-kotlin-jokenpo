package com.example.jokenpo.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.jokenpo.databinding.FragmentHomeBinding
import com.example.jokenpo.observers.FragmentObserver

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var goToPlayerAction: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater,container)
        getSavedInstanceState(savedInstanceState)
        initActions()
        initListeners()
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        lifecycle.addObserver(FragmentObserver())
    }

    private fun initActions() {
        goToPlayerAction = HomeFragmentDirections.actionHomeFragmentToPlayerFragment()
    }

    private fun initBinding(inflater: LayoutInflater,container: ViewGroup?) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    private fun initListeners() {
        binding.playButton.setOnClickListener {
            it.findNavController().navigate(goToPlayerAction)
         }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("myState", "HomeFragment - The stateful component's states comes here.")
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.getString("myState")?.let {
            Log.d("Jokenpo",it)
        }

    }
}