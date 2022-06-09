package com.example.abnamroassessment.presentation.ui.extensions

import android.content.Intent
import android.net.Uri

fun buildUrlIntent(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))