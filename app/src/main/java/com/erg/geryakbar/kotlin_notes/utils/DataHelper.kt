package com.erg.geryakbar.kotlin_notes.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE ="CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, note TEXT," +
                " date DATETIME)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addNote(title : String, note : String, date : String): Boolean{
        val db= this.writableDatabase
        val value = ContentValues()
        value.put("title",title)
        value.put("note",note)
        value.put("date",date)
        val result : Long = db.insert(TABLE_NAME,null, value)
        if(result < 0)
            return false
        else
        return true
    }

    fun readAllNote() : Cursor{
        val db = this.writableDatabase
        val res : Cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " ORDER BY date DESC", null)
        return res

    }

    fun readOneNote(id : String): Cursor{
        val db = this.writableDatabase
        val res : Cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE id = '"+id+"'", null)
        return res
    }

    fun updateNote (id: String, title: String, note: String, date: String): Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title",title)
        contentValues.put("note",note)
        contentValues.put("date",date)
        db.update(TABLE_NAME, contentValues," WHERE id ='"+id+"'",null)
        return true

    }

    companion object {
        private val DB_NAME="Notes.db"
        private val DB_VERSION=1
        private val TABLE_NAME="note"
    }

}