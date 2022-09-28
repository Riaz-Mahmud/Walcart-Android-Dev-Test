package com.backdoor.walcartandroidtest

import androidx.room.RoomOpenHelper.ValidationResult

object UnitTestEx {

    fun dateTestValidation(number: Int, text: String): Boolean {
        return !(number <= 0 || text.isEmpty())
    }
}