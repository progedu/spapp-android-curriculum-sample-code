package com.example.omikuji

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val img: ImageView = findViewById(R.id.img)
        val btn: Button = findViewById(R.id.btn)

        btn.setOnClickListener {
            val score = generateRandomNumber()
            img.setImageResource(R.drawable.omikuji)
            btn.visibility = View.INVISIBLE

            img.animate().rotation(180f).setDuration(2000L).withEndAction {
                img.setImageResource(getResult(score))
                img.rotation = 0f
                btn.visibility = View.VISIBLE
            }

            btn.text = getText(R.string.btn_text_again)
        }
    }
    private fun getResult(score: Int) = when (score) {
        in 91..100 -> R.drawable.daikichi
        in 71..90 -> R.drawable.chukichi
        in 51..70 -> R.drawable.shoukichi
        in 31..50 -> R.drawable.kichi
        in 11..30 -> R.drawable.kyou
        else -> R.drawable.daikyo
    }

    private fun generateRandomNumber() = (0..100).random()
}