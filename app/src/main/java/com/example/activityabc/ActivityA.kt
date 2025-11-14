package com.example.activityabc

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView.BufferType
import androidx.core.graphics.toColorInt
import kotlin.random.Random
import android.util.Log
import android.view.WindowManager
import android.widget.Toast

class ActivityA : AppCompatActivity() {
    private var activityBColor: String = "green"

    override fun onCreate(savedInstanceState: Bundle?) {
        val savedColor = savedInstanceState?.getString("Color")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_a)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val editText = findViewById<EditText>(R.id.edit_color)

        val buttonGenColor = findViewById<Button>(R.id.gen_new_color)

        buttonGenColor.setOnClickListener {
            val randColor = String.format("#%06X", 0xFFFFFF and Random.nextInt(0xFFFFFF))
            editText.setText(randColor, BufferType.EDITABLE)
        }

        val toast = Toast.makeText(this, "Invalid color input", Toast.LENGTH_SHORT)

        val buttonApplyColor = findViewById<Button>(R.id.apply_color)

        buttonApplyColor.setOnClickListener {
            val unchecked = editText.text.toString()
            if (unchecked.matches(Regex("#[a-fA-F0-9]{6}"))) {
                activityBColor = unchecked
                buttonApplyColor.setBackgroundColor(activityBColor.toColorInt())
            } else
                toast.show()
        }

        if (savedColor != null) {
            activityBColor = savedColor
            editText.setText(activityBColor, BufferType.EDITABLE)
            buttonApplyColor.setBackgroundColor(activityBColor.toColorInt())
        }

        val buttonA2B = findViewById<Button>(R.id.button_a_2_b)

        buttonA2B.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            if (activityBColor != "green")
                intent.putExtra("background", activityBColor)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        Log.d("ACTIVITI", "onNewIntent was called")
        super.onNewIntent(intent)
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        if (activityBColor != "green")
            outState.putString("Color", activityBColor)
        super.onSaveInstanceState(outState)
    }
}