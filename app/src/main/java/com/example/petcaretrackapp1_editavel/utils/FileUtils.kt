package com.example.petcaretrackapp1_editavel.utils

import android.content.Context
import android.net.Uri
import java.io.File

object FileUtils {
    fun getFileFromUri(context: Context, uri: Uri): File? {
        val cursor = context.contentResolver.query(uri, null, null, null, null) ?: return null
        cursor.use {
            if (it.moveToFirst()) {
                val filePath = it.getString(it.getColumnIndex("_data"))
                return File(filePath)
            }
        }
        return null
    }
}
