package com.natalyakreneva.hometask03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;

public class ContactListAdapter extends Adapter<ContactListAdapter.ListViewHolder> {
    private ArrayList<Contacts> contacts;
    private ContactListAdapter.OnItemClickListener listener;
    private Contacts contact;

    ContactListAdapter(ArrayList<Contacts> contacts, ContactListAdapter.OnItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    public ContactListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactListAdapter.ListViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ContactListAdapter.ListViewHolder holder, int position) {
        holder.bind((Contacts)this.contacts.get(position), this.listener);
    }

    public int getItemCount() {
        return this.contacts != null ? this.contacts.size() : 0;
    }

    public class ListViewHolder extends ViewHolder {
        private TextView contactName;
        private TextView contactPhoneNumber;
        private ImageView contactImage;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = (TextView)itemView.findViewById(R.id.contactName);
            contactPhoneNumber = (TextView)itemView.findViewById(R.id.contactPhoneNumber);
            contactImage = (ImageView)itemView.findViewById(R.id.contactImage);
        }

        public void bind(final Contacts contact, final ContactListAdapter.OnItemClickListener listener) {
            contactName.setText(contact.getName());
            contactPhoneNumber.setText(contact.getPhoneNumber());
            contactImage.setImageResource(contact.getImage());

            itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    listener.onItemClick(contact);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Contacts contact);
    }
}