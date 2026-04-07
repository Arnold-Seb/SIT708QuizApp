package com.example.sit708quizapp

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class QuizActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvProgress: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnSubmit: Button
    private lateinit var btnNext: Button
    private lateinit var btnThemeToggle: Button
    private lateinit var prefs: SharedPreferences

    private lateinit var optionButtons: List<Button>
    private val questions = QuestionRepository.getQuestions()

    private var currentIndex = 0
    private var score = 0
    private var selectedAnswerIndex = -1
    private var submitted = false
    private var username = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)

        val darkMode = prefs.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        username = intent.getStringExtra("USERNAME") ?: "User"

        tvWelcome = findViewById(R.id.tvWelcome)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvProgress = findViewById(R.id.tvProgress)
        progressBar = findViewById(R.id.progressBar)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnNext = findViewById(R.id.btnNext)
        btnThemeToggle = findViewById(R.id.btnThemeToggle)

        optionButtons = listOf(
            findViewById(R.id.btnOption1),
            findViewById(R.id.btnOption2),
            findViewById(R.id.btnOption3),
            findViewById(R.id.btnOption4)
        )

        tvWelcome.text = "Welcome, $username"
        updateThemeButtonText()

        btnThemeToggle.setOnClickListener {
            val currentlyDark = prefs.getBoolean("dark_mode", false)
            val newMode = !currentlyDark

            prefs.edit().putBoolean("dark_mode", newMode).apply()

            AppCompatDelegate.setDefaultNightMode(
                if (newMode) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

            recreate()
        }

        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!submitted) {
                    selectedAnswerIndex = index
                    highlightSelectedOption(index)
                }
            }
        }

        btnSubmit.setOnClickListener {
            if (selectedAnswerIndex == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            } else if (!submitted) {
                checkAnswer()
            }
        }

        btnNext.setOnClickListener {
            currentIndex++

            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            }
        }

        loadQuestion()
    }

    private fun updateThemeButtonText() {
        val darkMode = prefs.getBoolean("dark_mode", false)
        btnThemeToggle.text = if (darkMode) "Switch to Light Mode" else "Switch to Dark Mode"
    }

    private fun loadQuestion() {
        val question = questions[currentIndex]

        submitted = false
        selectedAnswerIndex = -1

        tvQuestion.text = question.questionText
        tvProgress.text = "Question ${currentIndex + 1} of ${questions.size}"
        progressBar.progress = ((currentIndex + 1) * 100) / questions.size

        optionButtons.forEachIndexed { index, button ->
            button.text = question.options[index]
            button.isEnabled = true
            button.setBackgroundColor(Color.LTGRAY)
        }

        btnSubmit.isEnabled = true
        btnNext.isEnabled = false
    }

    private fun highlightSelectedOption(selectedIndex: Int) {
        optionButtons.forEachIndexed { index, button ->
            if (index == selectedIndex) {
                button.setBackgroundColor(Color.CYAN)
            } else {
                button.setBackgroundColor(Color.LTGRAY)
            }
        }
    }

    private fun checkAnswer() {
        val correctIndex = questions[currentIndex].correctAnswerIndex
        submitted = true

        if (selectedAnswerIndex == correctIndex) {
            optionButtons[correctIndex].setBackgroundColor(Color.GREEN)
            score++
        } else {
            optionButtons[selectedAnswerIndex].setBackgroundColor(Color.RED)
            optionButtons[correctIndex].setBackgroundColor(Color.GREEN)
        }

        optionButtons.forEach { it.isEnabled = false }
        btnSubmit.isEnabled = false
        btnNext.isEnabled = true
    }
}