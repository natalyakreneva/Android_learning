package com.natalyakreneva.hometask05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natalyakreneva.hometask05.DAO.Contact


class ContactListAdapter internal constructor(contacts: List<Contact>, listener: OnItemClickListener) : RecyclerView.Adapter<ContactListAdapter.ListViewHolder>() {
    private var contacts: List<Contact> = contacts
    private val listener: OnItemClickListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(contacts[position], listener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
    internal fun setAllContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
    inner class ListViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName: TextView
        private val contactPhoneNumber: TextView
        private val contactImage: ImageView
        fun bind(contact: Contact, listener: OnItemClickListener) {
            contactName.setText(contact.name)
            contactPhoneNumber.setText(contact.phoneNumber)
            contactImage.setImageResource(contact.image)
            itemView.setOnClickListener { listener.onItemClick(contact) }
        }

        init {
            contactName = itemView.findViewById<TextView>(R.id.contactName)
            contactPhoneNumber = itemView.findViewById<TextView>(R.id.contactPhoneNumber)
            contactImage = itemView.findViewById<ImageView>(R.id.contactImage)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
    }

}