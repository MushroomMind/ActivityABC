package com.example.activityabc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.core.graphics.toColorInt

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_b)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        val buttonB2C = findViewById<Button>(R.id.button_b_2_c)

        buttonB2C.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        val text = findViewById<TextView>(R.id.color_info)

        val backgroundB = findViewById<View>(R.id.main)

        val colorB = intent.getStringExtra("background")
        if (colorB != null) {
            backgroundB.setBackgroundColor(colorB.toColorInt())
            text.setText(colorB, BufferType.EDITABLE)
        }
    }
}