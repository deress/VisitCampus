package com.dicoding.visitcampus.ui.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.visitcampus.data.VisitCampusRepository
import com.dicoding.visitcampus.data.model.chatbot.Chatbot
import com.dicoding.visitcampus.data.request.RequestChatbotBody
import kotlinx.coroutines.launch

class ChatbotViewModel(private val visitCampusRepository: VisitCampusRepository): ViewModel() {
    fun chatbot(userId: String, requestChatbotBdody: RequestChatbotBody) = visitCampusRepository.chatbot(userId, requestChatbotBdody)

    fun getChatbot(userId: String) = visitCampusRepository.getChatbot(userId)

    fun deleteChatbot(chabot: Chatbot) {
        viewModelScope.launch {
            visitCampusRepository.deleteChatbot(chabot)
        }
    }
}