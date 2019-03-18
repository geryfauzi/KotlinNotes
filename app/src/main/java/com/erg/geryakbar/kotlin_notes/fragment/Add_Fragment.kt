package com.erg.geryakbar.kotlin_notes.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.erg.geryakbar.kotlin_notes.R
import com.erg.geryakbar.kotlin_notes.utils.DataHelper
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.add_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

class Add_Fragment : Fragment(), View.OnClickListener {

    lateinit var mydb : DataHelper


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.add_fragment, container,false)

        mydb=DataHelper(context)
        view.btn_Save.setOnClickListener(this)
        return view
    }

    fun saveNote(){
        var title : String = et_Title.text.toString().trim()
        if (TextUtils.isEmpty(title)){
            et_Title.setError("Please, Insert a Title!")
            return
        }

        var note : String = et_Notes.text.toString().trim()
        if(TextUtils.isEmpty(note)){
            et_Notes.setError("Please, Insert a Note!")
            return
        }

        val sdf = SimpleDateFormat("dd/MMMM/yyyy")
        val date : String = sdf.format(Date())

        var isInserted : Boolean = mydb.addNote(title,note,date)
        if(isInserted){
            Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()
            et_Notes.setText("")
            et_Title.setText("")
        }
    }

    override fun onClick(v: View?) {
        saveNote()
    }


}