package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
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
        // Create the list adapter & set the adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listView.adapter = adapter

        /**
         * Set an ItemClickListener to the list view to navigate to the editing screen
         * setOnItemClickListener as opposed to setOnClickListener
         */
        listView.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            // Store the text in the item user clicks
            val entry = adapterView.getItemAtPosition(i).toString()
            Log.d(TAG, "onItemClick: You clicked on $entry")

            // get the id of the clicked entry item
            val data2 = entriesDbHelper.getItemId(entry)
            var itemId = -1
            // what's happening?
            while (data2.moveToNext()) {
                itemId = data2.getInt(0)
            }
            if (itemId > -1) {
                Log.d(TAG, "onItemClicked: The id of the clicked entry item is $itemId")
                // If everything's okay i.e., the id id received, navigate to the edit data screen
                val editScreenIntent = Intent(this, EditDataActivity::class.java)
                editScreenIntent.putExtra("id", itemId)
                editScreenIntent.putExtra("entry", entry)
                startActivity(editScreenIntent)
            } else {
                Toast.makeText(this, "No id associated with that name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}