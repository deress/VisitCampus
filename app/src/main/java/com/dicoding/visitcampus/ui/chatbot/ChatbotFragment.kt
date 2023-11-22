package com.dicoding.visitcampus.ui.chatbot

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.visitcampus.data.model.Chatbot
import com.dicoding.visitcampus.databinding.FragmentChatbotBinding

class ChatbotFragment : Fragment() {
    private var _binding: FragmentChatbotBinding? = null
    private val binding get() = _binding!!

    private lateinit var chatbotAdapter: ChatbotAdapter
    private lateinit var chatList: ArrayList<Chatbot>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatList = ArrayList()
        binding.rvChat.layoutManager = LinearLayoutManager(activity)
        binding.rvChat.adapter = ChatbotAdapter( requireActivity(), chatList)

        binding.btnSent.setOnClickListener {
            val chat = binding.etMessageBox.text.toString()
            chatList.add(Chatbot(null, chat, true))
            chatList.add(Chatbot(null, chat, false))
            Log.i("ChatbotFragment", "chatList: $chatList")
            binding.etMessageBox.setText("")
        }
    }
}