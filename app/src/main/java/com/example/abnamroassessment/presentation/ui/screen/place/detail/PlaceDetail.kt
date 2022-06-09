package com.example.abnamroassessment.presentation.ui.screen.place.detail

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abnamroassessment.R
import com.example.abnamroassessment.common.Constants
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail
import com.example.abnamroassessment.presentation.ui.extensions.buildUrlIntent

@Composable
fun PlaceDetail(
    placeDetail: PlaceDetail,
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val resources = LocalContext.current.resources

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 22.dp, start = 12.dp, end = 12.dp),
                textAlign = TextAlign.Center,
                text = placeDetail.name ?: Constants.EMPTY,
                style = MaterialTheme.typography.h1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                maxLines = 2,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                text = placeDetail.location?.address ?: Constants.EMPTY,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                text = placeDetail.location?.formatted_address ?: Constants.EMPTY,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                text = (resources.getString(R.string.detail_screen_timezone_prefix) + placeDetail.timezone),
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReposListPreview() {
    PlaceDetail(placeDetail = buildPlaceDetailPreview())
}