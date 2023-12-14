package com.dicoding.visitcampus.data.model.exam

object QuestionData {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "Which internet company began life as an online bookstore called 'Cadabra' ?",
            "ebay",
            "Shopify",
            "Amazon",
            "Overstock",
            3
        )
        questionsList.add(question1)

        val question2 = Question(
            1,
            "Which of the following languages is used as a scripting language in the Unity 3D game engine?",
            "Java",
            "C#",
            "C++",
            "Objective-C",
            2
        )
        questionsList.add(question2)

        val question3 = Question(
            1,
            "Which of these people was NOT a founder of Apple Inc?",
            "Jonathan Ive",
            "Steve Jobs ",
            "Ronald Wayne",
            "Steve Wozniak",
            1
        )
        questionsList.add(question3)
        return questionsList
    }
}