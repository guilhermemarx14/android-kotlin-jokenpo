package com.example.jokenpo.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.jokenpo.JokenpoEngine
import com.example.jokenpo.R
import com.example.jokenpo.Result
import com.example.jokenpo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    lateinit var engine: JokenpoEngine
    lateinit var resultText: TextView
    lateinit var gameResultText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)

        engine = JokenpoEngine(resources.getStringArray(R.array.spinner_options_array))

        val currentPlay = arguments?.getString("currentPlay")
        currentPlay?.let { updateResultText(it) }

        getSavedInstanceState(savedInstanceState)
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun updateResultText(currentPlay: String) {
        val resultGame = engine.calculateResult(currentPlay)

        resultText.text = when (resultGame) {
            Result.WIN -> getString(R.string.game_win)
            Result.LOSS -> getString(R.string.game_lost)
            else -> getString(R.string.game_draw)
        }

        gameResultText.text = when (resultGame) {
            Result.WIN -> getString(R.string.player_won)
            Result.LOSS -> getString(R.string.computer_won)
            else -> getString(R.string.draw)
        }
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        resultText = binding.resultText
        gameResultText = binding.gameResultText
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
            "ResultFragment - The stateful component's states comes here."
        )
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.getString("myState")?.let {
            Log.d("Jokenpo", it)
        }
    }
}