package com.example.testapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapp.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_splash, container, false)
        val tvCounterValue = rootView.findViewById<TextView>(R.id.tvCounterValue)
        val settings = activity!!.getPreferences(Context.MODE_PRIVATE)
        var count = settings.getInt(COUNTER, 0);
        tvCounterValue.setText(count.toString())
        val editor = settings.edit()
        editor.putInt(COUNTER, count.inc())
        editor.commit()
        return rootView
    }

    companion object {
        const val COUNTER = "counter"
    }
}