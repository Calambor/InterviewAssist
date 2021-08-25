package com.example.demo.repository

import com.example.demo.app.QID
import com.example.demo.app.Question

public abstract class Repository<K, T> {

    private val items = HashMap<K, T>()

    fun save(item: T) {
        items[item.getKey()] = item
    }


    fun get(key: K): T? = items[key]

    protected abstract fun T.getKey(): K

}

public class QuestionRepository : Repository<QID, Question>() {

    override fun Question.getKey() = id

}
