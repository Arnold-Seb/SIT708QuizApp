package com.example.sit708quizapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class ResultActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var tvResultTitle: TextView
    private lateinit var btnTakeNewQuiz: Button
    private lateinit var btnFinish: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)

        val darkMode = prefs.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvResultTitle = findViewById(R.id.tvResultTitle)
        tvScore = findViewById(R.id.tvScore)
        btnTakeNewQuiz = findViewById(R.id.btnTakeNewQuiz)
        btnFinish = findViewById(R.id.btnFinish)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val username = intent.getStringExtra("USERNAME") ?: "User"

        tvResultTitle.text = "$username's Quiz Result"
        tvScore.text = "Your score: $score/$total"

        btnTakeNewQuiz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            finishAffinity()
        }
    }
}