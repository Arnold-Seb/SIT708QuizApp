# SIT708 Quiz App

## Overview
This project is an Android Quiz Application developed for Credit Task 3.1. The app allows users to enter their name, attempt a multiple-choice quiz, track their progress, and view their final score at the end of the quiz.

## Features
- User name input on the main screen
- Multiple-choice quiz with answer selection
- Visual feedback for answers
  - Correct answer turns green
  - Incorrect selected answer turns red
- Progress bar showing quiz progress
- Result screen displaying final score
- "Take New Quiz" button to restart the quiz
- "Finish" button to close the application
- Dark mode / light mode toggle
- Session persistence for saved user name and theme preference

## Technologies Used
- Kotlin
- Android Studio
- XML layouts
- SharedPreferences for data persistence

## App Structure
The project contains the following main components:

- `MainActivity.kt`  
  Handles name input and starts the quiz.

- `QuizActivity.kt`  
  Displays quiz questions, handles answer submission, shows progress, and manages theme switching.

- `ResultActivity.kt`  
  Displays the final score and provides options to restart or finish the app.

- `Question.kt`  
  Data model for quiz questions.

- `QuestionRepository.kt`  
  Stores and provides the quiz questions.

## How the App Works
1. The user enters their name on the main screen.
2. The app saves the name using SharedPreferences.
3. The quiz begins with one question displayed at a time.
4. The user selects an answer and presses Submit.
5. The app highlights the result using colour feedback.
6. The user moves to the next question using the Next button.
7. At the end of the quiz, the app shows the final score on the result screen.
8. The user can either start a new quiz or finish the application.

## Theme Support
The app supports both light mode and dark mode.  
The selected theme is saved and remains active while navigating between screens.

## Screens Included
- Main screen
- Quiz screen
- Result screen

## Author
Arnold Sebastian

## Unit
SIT708 - Mobile Systems Development
