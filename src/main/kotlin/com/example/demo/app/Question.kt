package com.example.demo.app

typealias QID = String
typealias QIDv = Pair<QID, Int>

data class Question(
        val id: QID,
        val version: Int,
        val question: String,
        val expected: List<Expected> = emptyList(),
) {
}