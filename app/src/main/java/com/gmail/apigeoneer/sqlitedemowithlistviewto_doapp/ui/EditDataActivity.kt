package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.R
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.data.EntriesDbHelper

class EditDataActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "EditDataActivity"
    }

    private lateinit var entriesDbHelper: EntriesDbHelper
    private lateinit var etEditEntry: EditText
    private lateinit var btnDelete: Button
    private lateinit var btnSave: Button

    // extras to be passed
    private var selectedId = -1
    private var selectedEntry = "entry"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)

        etEditEntry = findViewById(R.id.editEntryEditText)
        btnDelete = findViewById(R.id.deleteButton)
        btnSave = findViewById(R.id.saveButton)

        entriesDbHelper = EntriesDbHelper(this)

        // Get the intent extra from the ListDataActivity
        val receivedIntent = intent
        // Now get the item id & text that was passed as an extra
        selectedId = receivedIntent.getIntExtra("id", -1)        // -1 is the default value in case the id doesn't exist
        selectedEntry = receivedIntent.getStringExtra("entry").toString()

        // Set text to show the current selected item
        etEditEntry.setText(selectedEntry)

        /**
         * Update the entry in the edit entry field on save button click
         */
        btnSave.setOnClickListener {
            val item = etEditEntry.text.toString()
            if (!item.equals("")) {
                entriesDbHelper.updateData(item, selectedId, selectedEntry)
            } else {
                Toast.makeText(this, "You must enter something.", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * Delete the entry from the db on delete button click
         */
        btnDelete.setOnClickListener {
            entriesDbHelper.deleteData(selectedId, selectedEntry)
            etEditEntry.setText("")
            Toast.makeText(this, "Removed from database", Toast.LENGTH_SHORT).show()
        }
    }
}


















