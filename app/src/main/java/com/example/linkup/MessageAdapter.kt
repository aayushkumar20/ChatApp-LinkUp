package com.example.linkup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(private val context:Context, private val messageList: ArrayList<Message>)
    : RecyclerView.Adapter<ViewHolder>() {

    private val iTEMRECEIVE=1
    private val iTEMSENT=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType==1){
            val view:View= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            ReceiveViewHolder(view)
        }else {
            val view:View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMessage=messageList[position]

        if(holder.javaClass == SentViewHolder::class.java)
        {

                val viewHolder=holder as SentViewHolder
                holder.sentMessage.text=currentMessage.message
        }
        else
        {
                val viewHolder=holder as ReceiveViewHolder
            holder.receiveMessage.text=currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage=messageList[position]
        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            iTEMSENT
        }else{
            iTEMRECEIVE
        }

    }

    override fun getItemCount(): Int {
        return messageList.size

    }

    class SentViewHolder(itemView: View) : ViewHolder(itemView){
  val sentMessage= itemView.findViewById<TextView>(R.id.txtsent)!!
    }
    class ReceiveViewHolder(itemView: View) : ViewHolder(itemView){
        val receiveMessage= itemView.findViewById<TextView>(R.id.txtreceive)!!
    }


}