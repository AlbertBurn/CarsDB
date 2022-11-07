package Utils

import android.provider.BaseColumns

class Util {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME="carsDB"
        const val TABLE_NAME="cars"

        const val KEY_ID="id"
        const val KEY_NAME="name"
        const val KEY_PRICE="price"

        const val CREATE_TABLE= "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_NAME TEXT," +
                "$KEY_PRICE TEXT)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}