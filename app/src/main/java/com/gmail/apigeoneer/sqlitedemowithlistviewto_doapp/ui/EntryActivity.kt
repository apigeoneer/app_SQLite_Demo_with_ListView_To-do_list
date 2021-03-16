package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.R
import com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
    }
}