package com.example.jokenpo.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.jokenpo.R
import com.example.jokenpo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding(inflater, container)
        getSavedInstanceState(savedInstanceState)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentResultBinding.inflate(inflater,container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("myState", "ResultFragment - The stateful component's states comes here.")
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.getString("myState")?.let {
            Log.d("Jokenpo",it)
        }
    }
}