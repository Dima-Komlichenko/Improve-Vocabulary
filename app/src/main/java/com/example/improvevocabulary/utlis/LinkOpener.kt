package com.example.improvevocabulary.utlis

import android.content.Context
import android.content.Intent
import android.net.Uri


object LinkOpener {

    private const val googleLink = "https://mail.google.com/mail/u/0/?ogbl#inbox?compose=GTvVlcRwPVgsPJHsJSWxTDBBxMVjsmMLTNRcqtQvSvLWpKCMtQGPBFHJbDFnsPvXvKTQCxfLMBhxC"

    fun startGoogle(context: Context) {
        val uri: Uri = Uri.parse(googleLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}