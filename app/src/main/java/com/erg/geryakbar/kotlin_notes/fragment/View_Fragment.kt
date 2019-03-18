package com.erg.geryakbar.kotlin_notes.fragment

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erg.geryakbar.kotlin_notes.R
import com.erg.geryakbar.kotlin_notes.adapter.Notes_Adapter
import com.erg.geryakbar.kotlin_notes.data.DataNotes
import com.erg.geryakbar.kotlin_notes.utils.DataHelper
import kotlinx.android.synthetic.main.view_fragment.*
import kotlinx.android.synthetic.main.view_fragment.view.*
import kotlin.collections.ArrayList

class View_Fragment : Fragment() {
    lateinit var mydb : DataHelper
    lateinit var listdata : MutableList<DataNotes>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.view_fragment, container,false)
        mydb= DataHelper(context)
        view.recyclerview.setHasFixedSize(true)
        view.recyclerview.layoutManager = GridLayoutManager(context,2)
        listdata = ArrayList()
        getData()
        view.recyclerview.adapter =Notes_Adapter(listdata,view.context)

        return view
    }

        fun getData(){
            var res : Cursor =mydb.readAllNote()
            if(res.count==0){
                Toast.makeText(context,"Your Note is Empty!",Toast.LENGTH_SHORT).show()
                return
            }
            while (res.moveToNext()){
                var dataNotes : DataNotes = DataNotes(res.getInt(0),res.getString(1)
                    ,res.getString(2),res.getString(3))
                    listdata.add(dataNotes)

            }
        }


}