package com.qrvault.app.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QRVaultDbHelper(context: Context) :
    SQLiteOpenHelper(context.applicationContext, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT NOT NULL,
                $COL_PHONE TEXT,
                $COL_EMAIL TEXT,
                $COL_VCARD TEXT NOT NULL,
                $COL_CREATED INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "QRVault.db"
        const val DB_VERSION = 1
        const val TABLE = "qr_history"
        const val COL_ID = "id"
        const val COL_NAME = "contact_name"
        const val COL_PHONE = "phone_number"
        const val COL_EMAIL = "email"
        const val COL_VCARD = "vcard_data"
        const val COL_CREATED = "created_at"
    }
}
