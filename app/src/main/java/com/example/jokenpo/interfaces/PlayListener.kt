package com.example.jokenpo.interfaces

import android.content.Context
import android.view.View
import android.widget.AdapterView
import com.example.jokenpo.R

interface PlayListener: AdapterView.OnItemSelectedListener {
    var thisContext: Context

    fun onPlaySelected(selectedPlay: String)

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val availablePlays = thisContext.resources.getStringArray(R.array.spinner_options_array)

        onPlaySelected(availablePlays[position])
        //Toast.makeText(this, "Selected play: $currentPlay", Toast.LENGTH_SHORT).show()
    }
}