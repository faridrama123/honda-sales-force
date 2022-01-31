package com.langit7.hondasalesforce.common

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

/**
 * Created by: Ranit Raj Ganguly on 17/04/21
 */
object FileShareUtils {
    /**
     * Access file from 'Application Directory'
     *
     * @param context - application context
     * @param fileName - name of file inside application directory to be shared
     * @return uri - returns URI of file.
     */
    fun accessFile(context: Context, fileName: String?): Uri? {
        val file = File(context.getExternalFilesDir(null), fileName)
        return if (file.exists()) {
            FileProvider.getUriForFile(
                context,
                "com.langit7.hondasalesforce.shareprovider", file
            )
        } else {
            null
        }
    }
}