package com.dicoding.visitcampus.ui.chatbot

import androidx.lifecycle.ViewModel
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.request.RequestChatbotBody

class ChatbotViewModel(private val visitCampusRepository: VisitCampusRepository): ViewModel() {
    fun chatbot(userId: String, requestChatbotBdody: RequestChatbotBody) = visitCampusRepository.chatbot(userId, requestChatbotBdody)

    fun getChatbot(userId: String) = visitCampusRepository.getChatbot(userId)
}