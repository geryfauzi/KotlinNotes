package com.erg.geryakbar.kotlin_notes.models

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.erg.geryakbar.kotlin_notes.fragment.Add_Fragment
import com.erg.geryakbar.kotlin_notes.fragment.Search_Fragment
import com.erg.geryakbar.kotlin_notes.fragment.View_Fragment
import com.erg.geryakbar.kotlin_notes.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(fragment = View_Fragment())
        navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment):Boolean{
        if(fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()
            return true
        }
        return false

    }



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        var fragment : Fragment = View_Fragment()
        when  (p0.itemId){
            R.id.navigation_view -> fragment=View_Fragment()
            R.id.navigation_add -> fragment=Add_Fragment()
            R.id.navigation_search -> fragment=Search_Fragment()
        }

        return loadFragment(fragment)
    }
}
