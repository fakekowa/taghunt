package com.appzet.taghunt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listview_item.view.*
import kotlinx.android.synthetic.main.room_item.view.*


class RoomsAdapter(var items: List<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.appzet.taghunt.R.layout.listview_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roomItem?.text = items[position]
        }
    }

class ViewHolder (view: View) : RecyclerView.ViewHolder(view)
{
    // Holds the TextView that will add each animal to
    val roomItem = view.listViewItemTextView
}
