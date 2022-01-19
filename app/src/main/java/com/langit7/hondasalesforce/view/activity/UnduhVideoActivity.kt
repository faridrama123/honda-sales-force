package com.langit7.hondasalesforce.view.activity

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.webkit.CookieManager
import android.webkit.DownloadListener
import android.webkit.URLUtil
import com.langit7.hondasalesforce.R
import kotlinx.android.synthetic.main.activity_unduh_video.*

class UnduhVideoActivity : AppCompatActivity() {

    lateinit var link : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unduh_video)

        link = intent.getSerializableExtra("apr") as String

        webUnduh.setDownloadListener(DownloadListener {
                link,
                userAgent,
                contentDescription,
                mimetype,
                contentLength ->

            // Initialize download request
            val request = DownloadManager.Request(Uri.parse(link))

            // Get the cookie
            val cookies = CookieManager.getInstance().getCookie(link)

            // Add the download request header
            request.addRequestHeader("Cookie",cookies)
            request.addRequestHeader("User-Agent",userAgent)

            // Set download request description
            request.setDescription("Downloading requested file....")

            // Set download request mime tytpe
            request.setMimeType(mimetype)

            // Allow scanning
            request.allowScanningByMediaScanner()

            // Download request notification setting
            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            // Guess the file name
            val fileName = URLUtil.guessFileName(link, contentDescription, mimetype)

            // Set a destination storage for downloaded file
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

            // Set request title
            request.setTitle(URLUtil.guessFileName(link, contentDescription, mimetype));


            // DownloadManager request more settings
            request.setAllowedOverMetered(true)
            request.setAllowedOverRoaming(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                request.setRequiresCharging(false)
                request.setRequiresDeviceIdle(false)
            }
            request.setVisibleInDownloadsUi(true)


            // Get the system download service
            val dManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            // Finally, request the download to system download service
            dManager.enqueue(request)
        })

        println("link :" + link)
        webUnduh.loadUrl(link)
    }
}
