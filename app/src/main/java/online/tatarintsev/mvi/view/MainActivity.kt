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
        sp = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
        val isNightTheme: Boolean? = sp?.getBoolean(APP_NIGHT_THEME, false)
        if(isNightTheme == true) {
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }

        delegate.applyDayNight()

//        setTheme(theme);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
    }

    fun saveNightTheme(isNighTheme: Boolean) {
        val editor: SharedPreferences.Editor? = sp?.edit()
        editor?.putBoolean(APP_NIGHT_THEME, isNighTheme)
        editor?.apply()
    }
}



