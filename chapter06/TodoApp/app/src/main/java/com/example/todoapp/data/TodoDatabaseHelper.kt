package com.example.todoapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TodoDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "todo_app.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // テーブルを作成
        val sql = "CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, priority TEXT NOT NULL)"
        db?.execSQL(sql)
        // サンプルデータの追加
        db?.execSQL("INSERT INTO tasks (name, priority) VALUES (\"タスク\", \"高\" )")
        db?.execSQL("INSERT INTO tasks (name, priority) VALUES (\"タスク２\", \"中\" )")
        db?.execSQL("INSERT INTO tasks (name, priority) VALUES (\"タスク３\", \"低\" )")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
    }
}