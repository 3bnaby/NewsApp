package com.example.newsapp.api.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<Source?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)