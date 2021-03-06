package com.example.demo.view

import com.example.demo.app.Expected
import com.example.demo.app.Question
import com.example.demo.app.Styles
import javafx.scene.paint.Color
import tornadofx.*

private val lifeQuestion =
    Question("questionId", 1, "What is the answer to life, the universe and everything?", listOf(Expected("42")))

class MainView : View("Interview Assist") {

    override val root = vbox {
        label(title) {
            addClass(Styles.heading)
        }
        add(InterviewQuestionRow(lifeQuestion))
        add(InterviewQuestionRow(lifeQuestion))
    }

}

class InterviewQuestionRow(val question: Question = lifeQuestion) : View() {
    private val expectedSection = vbox {
        paddingAll = 20
        label(if (question.expected.size == 1) "Expected answer:" else "Expected answers:")
        for (expected in question.expected) {
            hbox {
                label(expected.answer)
                // TODO draw score input //label("1 2 3 4 5")
            }
        }
    }

    override val root = vbox {
        paddingAll = 10
        style {
            borderColor += box(Color.RED)
        }
        label(question.question) {
            isWrapText = true
        }
        if (question.expected.isNotEmpty()) {
            add(expectedSection)
        }
        label("Answer notes:")
        textarea() {
            prefRowCount = 3
        }
        label("Question feedback:")
        textarea() {
            prefRowCount = 2
        }
    }

}