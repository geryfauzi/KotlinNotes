package com.erg.geryakbar.kotlin_notes.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erg.geryakbar.kotlin_notes.R
import com.erg.geryakbar.kotlin_notes.data.DataNotes
import kotlinx.android.synthetic.main.list_notes.view.*

class Notes_Adapter : RecyclerView.Adapter<Notes_Adapter.ViewHolder>{

   lateinit var listdata : MutableList<DataNotes>
   lateinit var inflater : LayoutInflater
   lateinit var context : Context


    constructor(listdata : MutableList<DataNotes>, context: Context):super(){
        this.context=context
        this.inflater= LayoutInflater.from(context)
        this.listdata=listdata
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view : View = inflater.inflate(R.layout.list_notes, p0, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listnote : DataNotes =listdata.get(position)
        holder.id.text = listnote.id.toString()
        holder.title.text = listnote.title
        holder.note.text = listnote.note
        holder.date.text = listnote.date

    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var id = itemView.txt_Id
        var title = itemView.txt_Title
        var date = itemView.txt__Date
        var note = itemView.txt_Note

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Toast.makeText(itemView.context,itemView.txt_Id.text.toString(),Toast.LENGTH_SHORT).show()
        }

    }
}