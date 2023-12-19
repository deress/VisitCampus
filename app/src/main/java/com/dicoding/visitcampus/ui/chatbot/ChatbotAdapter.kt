package com.dicoding.visitcampus.ui.chatbot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.chatbot.Chatbot

class ChatbotAdapter(private val context: Context) :
    ListAdapter<Chatbot, RecyclerView.ViewHolder>(ChatDiffCallback()) {
    private var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return if (viewType == 1) {
            SentViewHolder(inflater.inflate(R.layout.sent, parent, false))
        } else {
            ReceiveViewHolder(inflater.inflate(R.layout.receive, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentChat = getItem(position)
        if (holder.itemViewType == 1) {
            (holder as SentViewHolder).bind(currentChat)
        } else {
            (holder as ReceiveViewHolder).bind(currentChat)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = getItem(position)
        return if (chat.isUser) {
            1
        } else {
            2
        }
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sentChat = itemView.findViewById<TextView>(R.id.tv_sent)

        fun bind(chat: Chatbot) {
            sentChat.text = chat.chat
        }
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val receiveChat = itemView.findViewById<TextView>(R.id.tv_receive)

        fun bind(chat: Chatbot) {
            receiveChat.text = chat.chat
        }
    }

    private class ChatDiffCallback : DiffUtil.ItemCallback<Chatbot>() {
        override fun areItemsTheSame(oldItem: Chatbot, newItem: Chatbot): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chatbot, newItem: Chatbot): Boolean {
            return oldItem == newItem
        }
    }
}