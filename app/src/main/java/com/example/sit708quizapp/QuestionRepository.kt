package com.example.sit708quizapp

object QuestionRepository {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                "What does CPU stand for?",
                listOf(
                    "Central Process Unit",
                    "Central Processing Unit",
                    "Computer Processing Utility",
                    "Core Processing Unit"
                ),
                1
            ),
            Question(
                "Which programming language is officially recommended for Android development?",
                listOf(
                    "Swift",
                    "Kotlin",
                    "Python",
                    "PHP"
                ),
                1
            ),
            Question(
                "Which company developed Android?",
                listOf(
                    "Google",
                    "Apple",
                    "Microsoft",
                    "Samsung"
                ),
                0
            ),
            Question(
                "What is XML commonly used for in Android?",
                listOf(
                    "Designing layouts",
                    "Storing videos",
                    "Running games",
                    "Creating databases"
                ),
                0
            ),
            Question(
                "Which UI component is used to show progress visually?",
                listOf(
                    "EditText",
                    "ImageView",
                    "ProgressBar",
                    "TextView"
                ),
                2
            )
        )
    }
}