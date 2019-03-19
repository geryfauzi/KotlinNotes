package com.erg.geryakbar.kotlin_notes.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erg.geryakbar.kotlin_notes.R
import com.erg.geryakbar.kotlin_notes.adapter.Notes_Adapter
import com.erg.geryakbar.kotlin_notes.data.DataNotes
import com.erg.geryakbar.kotlin_notes.utils.DataHelper
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*

class Search_Fragment : Fragment(), View.OnKeyListener {

    lateinit var mydb : DataHelper
    lateinit var listdata: MutableList<DataNotes>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.search_fragment, container,false)
        mydb = DataHelper(context)
        listdata= ArrayList()
        view.recyclerviewsearch.setHasFixedSize(true)
        view.recyclerviewsearch.layoutManager = LinearLayoutManager(context)
        view.et_Search.setOnKeyListener(this)
        return view
    }

    fun searchData(){
        listdata.clear()
       var query  = et_Search.text.toString().trim()
        if(TextUtils.isEmpty(query)){
            et_Search.setError("Please, insert a search query!")
            return
        }

        var res = mydb.searchNote(query)
        if(res.count==0){
            Toast.makeText(context,"Can't find the note you're looking for!",Toast.LENGTH_SHORT).show()
            return
        }

        while (res.moveToNext()){
            var dataNotes : DataNotes = DataNotes(res.getInt(0),res.getString(1)
                ,res.getString(2),res.getString(3).substring(0,10))
            listdata.add(dataNotes)
        }
        recyclerviewsearch.adapter= Notes_Adapter(listdata, this!!.context!!)

    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if(event.action==KeyEvent.ACTION_DOWN){
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    searchData()
                    return true
                } else if(event.action==KeyEvent.KEYCODE_DEL)
                    listdata.clear()
                    return true
            }
        }
        return false
    }

}