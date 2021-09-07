package com.example.common.dataflow

import com.example.demo.Question
import com.example.demo.User

fun mapQuestionsUsers(qid: String, version: Long, question: String, owner: String, oid: String, oname: String) =
    Pair(Question(qid, version, question, owner), User(oid, oname))
