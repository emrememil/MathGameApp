package com.example.mathgameapp.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mathgameapp.Model.Shopping

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VER){

    companion object{
        private val DATABASE_VER=1
        private val DATABASE_NAME="MATH.db"

        //Table

        private  val TABLE_NAME="INFO"
        private  val COL_ID="ID"
        private  val COL_NAME="NAME"
        private  val COL_CLASS="CLASS"
        private  val COL_COIN="COIN"
        private  val COL_SUREGELISIM="SUREGEL"
        private  val COL_COINGELISIM="COINGEL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_CLASS INTEGER, $COL_COIN INTEGER, $COL_SUREGELISIM INTEGER, $COL_COINGELISIM INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD

    val tumBilgiler:List<Shopping>
        get() {
            val listUsers = ArrayList<Shopping>()
            val selectQuery= "SELECT * FROM $TABLE_NAME"
            val db=this.writableDatabase
            var cursor=db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val bilgiler = Shopping()
                    bilgiler.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    bilgiler.isim = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    bilgiler.sinif = cursor.getInt(cursor.getColumnIndex(COL_CLASS))
                    bilgiler.coin = cursor.getInt(cursor.getColumnIndex(COL_COIN))
                    bilgiler.sure_gel = cursor.getInt(cursor.getColumnIndex(COL_SUREGELISIM))
                    bilgiler.coin_gel = cursor.getInt(cursor.getColumnIndex(COL_COINGELISIM))

                    listUsers.add(bilgiler)
                }while (cursor.moveToNext())
            }
            db.close()
            return  listUsers
    }

    fun  addUser(shopping: Shopping){
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID,shopping.id)
        values.put(COL_NAME,shopping.isim)
        values.put(COL_CLASS,shopping.sinif)
        values.put(COL_COIN,shopping.coin)
        values.put(COL_SUREGELISIM,shopping.sure_gel)
        values.put(COL_COINGELISIM,shopping.coin_gel)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun  updateUser(shopping: Shopping): Int {
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID,shopping.id)
        values.put(COL_NAME,shopping.isim)
        values.put(COL_CLASS,shopping.sinif)
        values.put(COL_COIN,shopping.coin)
        values.put(COL_SUREGELISIM,shopping.sure_gel)
        values.put(COL_COINGELISIM,shopping.coin_gel)

        return db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(shopping.id.toString()))

    }

    fun  deleteUser(shopping: Shopping){
        val db=this.writableDatabase


         db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(shopping.id.toString()))
         db.close()
    }

}