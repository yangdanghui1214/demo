package com.ydh.component

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ydh.component.handler.HandlerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler?.setOnClickListener {
            startActivity(Intent(this, HandlerActivity::class.java))
        }

    }
}
