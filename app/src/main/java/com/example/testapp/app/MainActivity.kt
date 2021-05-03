package com.example.testapp.app

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.fragments.JokesFragment
import com.example.testapp.fragments.SplashFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var splashFragment: SplashFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TestApp)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            if (splashFragment == null) {
                splashFragment = SplashFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main, splashFragment!!)
                    .commit()
                GlobalScope.launch {
                    delay(3000L)
                    val jokesFragment = JokesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, jokesFragment)
                        .commit()
                }
            }
        }
    }
}