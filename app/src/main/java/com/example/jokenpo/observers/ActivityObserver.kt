package com.example.jokenpo.observers

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class ActivityObserver: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("Jokenpo", "Activity: ${source.javaClass.name} - Event: $event")
    }
}