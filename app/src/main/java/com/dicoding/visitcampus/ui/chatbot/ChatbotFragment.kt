package com.dicoding.visitcampus.ui.chatbot

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.chatbot.Chatbot
import com.dicoding.visitcampus.data.request.RequestChatbotBody
import com.dicoding.visitcampus.databinding.FragmentChatbotBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.dicoding.visitcampus.ui.main.MainViewModel

class ChatbotFragment : Fragment() {
    private lateinit var userId: String
    private var _binding: FragmentChatbotBinding? = null
    private val binding get() = _binding!!
    private val chatbotViewModel by viewModels<ChatbotViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private lateinit var chatbotAdapter: ChatbotAdapter
    private lateinit var recyclerView: RecyclerView
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
        recyclerView = binding.rvChat
        chatbotAdapter = ChatbotAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = chatbotAdapter

        viewModel.getSession().observe(viewLifecycleOwner){user ->
            userId = user.userId
            chatbotViewModel.getChatbot(userId).observe(viewLifecycleOwner){ chatbot ->
                updateChatList(chatbot)
                binding.btnClearChat.setOnClickListener {
                    showDeleteConfirmationDialog(chatbot)
                }
            }
        }

        binding.btnSent.setOnClickListener {
            if (binding.etMessageBox.text.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), "Please write your message!", Toast.LENGTH_SHORT).show()
            } else {
                val chat = binding.etMessageBox.text.toString()
                Log.i("ChatbotFragment", "userId: $userId, chat: $chat")
                chatbotViewModel.chatbot(userId, RequestChatbotBody(chat)).observe(viewLifecycleOwner){

                }
                binding.etMessageBox.setText("")
            }
        }
    }

    private fun updateChatList(newChatList: List<Chatbot>) {
        chatbotAdapter.submitList(newChatList)
    }

    private fun showDeleteConfirmationDialog(chatbot: List<Chatbot>) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation")
            .setMessage(getString(R.string.confirm_delete_chatbot))
            .setPositiveButton("Delete") { _, _ ->
                // User clicked the positive button (Delete)
                for (chat in chatbot) {
                    chatbotViewModel.deleteChatbot(chat)
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // User clicked the negative button (Cancel)
                dialog.dismiss()
            }
            .show()
    }
}