package online.tatarintsev.mvi.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import online.tatarintsev.mvi.R



class MainActivity: AppCompatActivity() {

    private val APP_SETTINGS = "APP_SETTINGS"
    private val APP_NIGHT_THEME = "NIGHT_THEME"

    private var sp: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
    }


}



