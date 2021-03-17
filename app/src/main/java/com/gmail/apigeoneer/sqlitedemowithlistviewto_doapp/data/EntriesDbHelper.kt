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
     * Adds data to the db
     * @param item
     * @return Boolean
     */
    fun addData(item: String) : Boolean {

        // Create a SQLite Database obj (getWritableDatabase)
        val db = this.writableDatabase
        // Create a ContentValues obj
        val contentValues = ContentValues()
        // Add first value to Content values
        contentValues.put(COL1, item)
        Log.d(TAG, "addData: adding $item to $TABLE_NAME.")

        // Store if the data was entered correctly or not
        val result = db.insert(TABLE_NAME, null, contentValues)
        // if data is inserted incorrectly, -1 will be returned
        return !result.equals(-1)
    }

    /**
     * Returns all the data from the db
     * @return Cursor
     */
    fun getData() : Cursor {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        return db.rawQuery(query, null)
    }

    /**
     * Returns the id that matches the entry passed in
     * @param entry
     * @return Cursor
     */
    fun getItemId(entry: String) : Cursor {
        val db = this.writableDatabase
        val query = "SELECT $COL0 FROM $TABLE_NAME WHERE $COL1 = '$entry'"
        return db.rawQuery(query, null)
    }

    /**
     * Updates the entry in the db
     * @param newName
     * @param id
     * @param oldName
     */
    fun updateData(newName: String, id: Int, oldName: String) {
        val db = this.writableDatabase
        val query = "UPDATE $TABLE_NAME SET $COL1 = '$newName' WHERE $COL0 = '$id' AND $COL1 = '$oldName"
        Log.d(TAG, "updateData: query: $query")
        Log.d(TAG, "updateData: Changing the entry to '$newName'")
        db.execSQL(query)
    }

    /**
     * Deletes the entry from the db
     * @param id
     * @param entry
     */
    fun deleteData(id: Int, entry: String) {
        val db = this.writableDatabase
        val query = "DELETE FROM $TABLE_NAME WHERE $COL0 = '$id' AND $COL1 = '$entry'"
        Log.d(TAG, "deleteData: query: $query")
        Log.d(TAG, "deleteData: Deleting $entry from database.")
        db.execSQL(query)
    }

}