package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.R
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.data.EntriesDbHelper

class EntryActivity : AppCompatActivity() {

    private lateinit var entriesDbHelper: EntriesDbHelper
    private lateinit var clNewEntry: ConstraintLayout
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var etEntry: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        clNewEntry = findViewById(R.id.newEntryConstraintLayout)
        val animationDrawable = clNewEntry.background
        // not working , methods not found

        btnAdd = findViewById(R.id.addButton)
        btnView = findViewById(R.id.viewButton)
        etEntry = findViewById(R.id.entryEditText)

        entriesDbHelper = EntriesDbHelper(this)

        /**
         * Add click listener to the add button
         * to add data in the etEntry field to the db
         */
        btnAdd.setOnClickListener {
            val entry = etEntry.text.toString()

            if (etEntry.length() != 0) {
                eAddData(entry)
                etEntry.setText("")
            } else {
                Toast.makeText(this, "You must write something!", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * Add click listener to the view button
         * to navigate to the screen with the list of to dos
         */
        btnView.setOnClickListener {
            val intent = Intent(this, ListDataActivity::class.java)
            startActivity(intent)
        }
    }

    fun eAddData(newEntry: String) {
        val insertData = entriesDbHelper.addData(newEntry)

        if (insertData) {
            Toast.makeText(this, "Data inserted successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Could not insert data.", Toast.LENGTH_SHORT).show()
        }
    }
}