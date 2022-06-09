package com.example.abnamroassessment.common

import android.content.Intent
import android.net.Uri
import com.example.abnamroassessment.presentation.ui.extensions.buildUrlIntent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntentExtensionKtTest {

    @Test
    fun `Building a Url intent should return a valid Intent`() {
        // Given
        val url = "http://www.google.com"
        val urlUri = Uri.parse(url)

        // When
        val intent = buildUrlIntent(url)

        // Then
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(urlUri, intent.data)
    }

}
