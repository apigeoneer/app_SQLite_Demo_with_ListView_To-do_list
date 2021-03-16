package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.R
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.data.EntriesDbHelper

class ListDataActivity : AppCompatActivity() {

    private lateinit var entriesDbHelper: EntriesDbHelper
    private lateinit var listView: ListView

    companion object {
        private const val TAG = "ListDataActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data)

        entriesDbHelper = EntriesDbHelper(this)
        listView = findViewById(R.id.listView)

        populateListView()
    }

    private fun populateListView() {
        Log.d(TAG, "populateListView: displaying data in the list view")

        /**
         * get the data & append it to a list
         * entriesDbHelper.getData() returns all the rows from the 'query' in the getData() method in the DbHelper class
         */
        val data = entriesDbHelper.getData()
        // loop through all the rows in 'data'
        val listData: ArrayList<String> = ArrayList()
        while (data.moveToNext()) {
            // get the value from the db in col 1 i.e. 'entry' col
            // then add it to the ArrayList
            listData.add(data.getString(1))
        }
        // create the list adapter & set the adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listView.adapter = adapter
    }
}