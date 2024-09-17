package com.example.newsapp.ui.model

import com.example.newsapp.R

data class Category(
    val id: String,
    val imageId: Int,
    val backgroundColorId: Int,
    val title: String,
    val isLeftSided: Boolean
) {
    companion object {
        val categories = listOf(
            Category(
                id = "sports",
                imageId = R.drawable.sports,
                backgroundColorId = R.color.red,
                title = "Sports",
                isLeftSided = true
            ),
            Category(
                id = "entertainment",
                imageId = R.drawable.entertainment,
                backgroundColorId = R.color.blue,
                title = "Entertainment",
                isLeftSided = false
            ),
            Category(
                id = "health",
                imageId = R.drawable.health,
                backgroundColorId = R.color.pink,
                title = "Health",
                isLeftSided = true
            ),
            Category(
                id = "business",
                imageId = R.drawable.business,
                backgroundColorId = R.color.orange,
                title = "Business",
                isLeftSided = false
            ),
            Category(
                id = "technology",
                imageId = R.drawable.technology,
                backgroundColorId = R.color.light_blue,
                title = "Technology",
                isLeftSided = true
            ),
            Category(
                id = "science",
                imageId = R.drawable.science,
                backgroundColorId = R.color.yellow,
                title = "Science",
                isLeftSided = false
            )

        )
    }

}