package com.wynsel.samplenetherdrake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wynsel.samplenetherdrake.counter.CounterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button?.setOnClickListener {
            startActivity(Intent(this, CounterActivity::class.java))
        }
    }
}
