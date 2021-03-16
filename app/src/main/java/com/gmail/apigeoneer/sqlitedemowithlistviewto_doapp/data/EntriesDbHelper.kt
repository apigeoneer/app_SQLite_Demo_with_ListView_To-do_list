package com.gmail.apigeoneer.sqlitedemowithlistviewto_doapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


private const val TABLE_NAME: String = "entries_table"
private const val DATABASE_VERSION: Int = 1
private const val COL0: String = "ID"
private const val COL1: String = "entry"

class EntriesDbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    TABLE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val TAG = "EntriesDatabaseHelper"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable: String = "CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL1 TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i1: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    /**
     * Add data to the db
     */
    fun addData(item: String) : Boolean {

        // Create a SQLite Database obj (getWritableDatabase)
        val db = this.writableDatabase
        // Create a ContentValues obj
        val contentValues = ContentValues()
        // Add first value to Content values
        contentValues.put(COL1, item)
        Log.d(TAG, "addData: adding $item to $TABLE_NAME...")

        // Store if the data was entered correctly or not
        val result = db.insert(TABLE_NAME, null, contentValues)
        // if data is inserted incorrectly, -1 will be returned
        return !result.equals(-1)
    }

    /**
     * Return all the data from the db
     */
    fun getData() : Cursor {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        return db.rawQuery(query, null)
    }

}