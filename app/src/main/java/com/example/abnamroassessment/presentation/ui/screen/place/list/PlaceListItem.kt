package com.example.abnamroassessment.presentation.ui.screen.place.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.abnamroassessment.R
import com.example.abnamroassessment.common.Constants.EMPTY
import com.example.abnamroassessment.domain.model.place.search.Place

@Composable
fun PlaceListItem(
    place: Place,
    onItemClick: (Place) -> Unit
) {
    val paddingXXSmall = dimensionResource(id = R.dimen.padding_xxsmall)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val avatarSize = dimensionResource(id = R.dimen.avatar_size_medium)
    val dividerStartIndent = dimensionResource(id = R.dimen.place_list_item_start_indent)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(place)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            Column {
                Text(
                    text = place.name ?: EMPTY,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(paddingXXSmall))

                Text(
                    text = place.location?.address ?: EMPTY,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Divider(
            startIndent = dividerStartIndent,
            modifier = Modifier.padding(end = paddingMedium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListItemPreview() {
    PlaceListItem(place = buildPlacePreview(), onItemClick = {})
}