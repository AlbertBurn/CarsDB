package data

import Utils.Util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import model.Car


class DataBaseHandler(context: Context) :
    SQLiteOpenHelper(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Util.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Util.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addCar(car: Car) {
        val db: SQLiteDatabase = this.writableDatabase

        val values = ContentValues()
        values.put(Util.KEY_NAME, car.name)
        values.put(Util.KEY_PRICE, car.price)

        db.insert(Util.TABLE_NAME, null, values)
        db.close()
    }

    fun getCar(id: Int): Car? {
        val db: SQLiteDatabase = this.readableDatabase
        var car: Car? = null
        val cursor = db.query(
            Util.TABLE_NAME,   // The table to query
            arrayOf(
                Util.KEY_ID,
                Util.KEY_NAME,
                Util.KEY_PRICE
            ),             // The array of columns to return (pass null to get all)
            "${Util.KEY_ID}=?", arrayOf(id.toString()), // The columns for the WHERE clause
            null,       // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
        if (cursor != null) {
            try {
                cursor.moveToFirst()
                car = Car(cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2))
            } finally {
                cursor.close()
            }

        }

        return car
    }

    fun getAllCars(): ArrayList<Car> {
        val db: SQLiteDatabase = this.readableDatabase
        val carsList = ArrayList<Car>()
        val selsectAllCars = "SELECT * FROM ${Util.TABLE_NAME}"
        val cursor: Cursor = db.rawQuery(selsectAllCars, null)

        if (cursor.moveToFirst()) {
            try {
                do {
                    carsList.add(Car(cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2)))
                } while (cursor.moveToNext())
            } finally {
                cursor.close()
            }
        }

        return carsList
    }

    fun updateCar(car: Car): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Util.KEY_NAME, car.name)
        contentValues.put(Util.KEY_PRICE, car.price)
        val whereclause = "${Util.KEY_ID}=?"
        val whereargs = arrayOf(car.id.toString())
        return db.update(Util.TABLE_NAME, contentValues, whereclause, whereargs)
    }

    fun deleteCar(car: Car) {
        val db: SQLiteDatabase = this.writableDatabase
        val whereclause = "${Util.KEY_ID}=?"
        val whereargs = arrayOf(car.id.toString())
        db.delete(Util.TABLE_NAME, whereclause, whereargs)
        db.close()
    }

    fun getCarsCount(): Int {
        val db: SQLiteDatabase = this.readableDatabase
        val countQuery: String = "SELECT * FROM ${Util.TABLE_NAME}"
        val cursor: Cursor = db.rawQuery(countQuery, null)
        return cursor.count
    }
}


