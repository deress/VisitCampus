package com.dicoding.visitcampus.ui.chatbot

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.model.Chatbot

class ChatbotAdapter(val context: Context, val chatList: ArrayList<Chatbot>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_SENT = 1
    val ITEM_RECEIVE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }
        else {
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            return ReceiveViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentChat = chatList[position]
        if (holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.sentChat.text = currentChat.chat
        } else {
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveChat.text = "Thanks for using Chatbot feature, this message replay by Chatbot."
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = chatList[position]
        if (chat.isUser) {
            return 1
        } else {
            return 2
        }
    }

    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sentChat = itemView.findViewById<TextView>(R.id.tv_sent)
    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val receiveChat = itemView.findViewById<TextView>(R.id.tv_receive)
    }
}