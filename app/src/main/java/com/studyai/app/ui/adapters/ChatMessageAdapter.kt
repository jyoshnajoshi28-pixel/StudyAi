package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.ChatMessage
import com.studyai.app.databinding.ItemChatMessageBinding

class ChatMessageAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<ChatMessageAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(private val binding: ItemChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.chatUserMessage.text = "You: ${message.userMessage}"
            binding.chatAiResponse.text = "AI: ${message.aiResponse}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size
}
