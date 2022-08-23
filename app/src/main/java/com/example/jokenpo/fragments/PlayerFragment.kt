package com.example.jokenpo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.jokenpo.R
import com.example.jokenpo.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var spinner: Spinner
    private lateinit var onItemSelectedListener: OnItemSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onItemSelectedListener = context as OnItemSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        getSavedInstanceState(savedInstanceState)
        setupSpinner()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupSpinner() {
        spinner = binding.spinner
        val adapter =
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinner_options_array,
                android.R.layout.simple_spinner_item
            )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = onItemSelectedListener
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(
            "myState",
            "PlayerFragment - The stateful component's states comes here."
        )
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.getString("myState")?.let {
            Log.d("Jokenpo", it)
        }
    }

}