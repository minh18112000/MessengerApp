package com.example.messengerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

const val ITEM_SEND = 1
const val ITEM_RECEIVE = 2

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sendMessage = itemView.findViewById<TextView>(R.id.txt_send_message)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view: View = LayoutInflater.from(context).inflate(
                R.layout.send,
                parent,
                false
            )
            return SendViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(
                R.layout.receive,
                parent,
                false
            )
            return ReceiveViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: RecyclerView.ViewHolder
        val currentMessage = messageList[position]
        if (holder.javaClass == SendViewHolder::class.java) {
            viewHolder = holder as SendViewHolder
            viewHolder.sendMessage.text = currentMessage.message
        } else {
            viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemCount() = messageList.size

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid == currentMessage.senderId) {
            return ITEM_SEND
        } else {
            return ITEM_RECEIVE
        }
    }

}