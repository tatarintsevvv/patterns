package online.tatarintsev.mvi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.tatarintsev.mvi.R



class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
    }

}



